package org.example.servicecontrats.service;

import org.example.serviceclients.service.NotificationService;
import org.example.servicecontrats.KafkaNotificationProducer;
import org.example.servicecontrats.entity.Contrat;
import org.example.servicecontrats.repository.ContratRepository;
import org.example.servicenotifications.NotificationMessage;
import org.springframework.stereotype.Service;
import org.example.serviceclients.mapper.ClientMapper;
import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.example.servicecontrats.feignclients.ClientServiceClient;
import org.example.servicecontrats.mappers.ContratMapper;


import java.util.Optional;


@Service
public class ContratService {

    private final KafkaNotificationProducer kafkaProducer;


    public final ContratRepository contratRepository;

    private final ClientServiceClient clientServiceClient;

    private final ContratMapper contratMapper;

    private final ClientMapper clientMapper;



    public ContratService(KafkaNotificationProducer kafkaProducer, ContratRepository contratRepository, ClientServiceClient clientServiceClient, ContratMapper contratMapper, ClientMapper clientMapper) {
        this.kafkaProducer = kafkaProducer;
        this.contratRepository = contratRepository;
        this.clientServiceClient = clientServiceClient;
        this.contratMapper = contratMapper;
        this.clientMapper = clientMapper;

    }


    public ContratDTO creerContrat(ContratDTO contratDTO) {
        // Vérifier si le client existe
        ClientDTO clientDTO = clientServiceClient.getClientById(contratDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Le client n'existe pas."));

        // Vérifications métier
        if (clientDTO.getAge() < 18) {
            throw new IllegalArgumentException("Le client doit avoir au moins 18 ans.");
        }
        if (contratDTO.getDureeEnAnnees() < 1) {
            throw new IllegalArgumentException("La durée du contrat doit être supérieure à 1 an.");
        }
        if (contratDTO.getMontantAssure() <= 10000) {
            throw new IllegalArgumentException("Le montant assuré doit être supérieur à 10 000 euros.");
        }

        // Convertir DTO en entité
        Contrat contrat = contratMapper.toContrat(contratDTO);
        contrat.setClient(clientMapper.toClient(clientDTO)); // Résolution explicite du client

        // Sauvegarder dans la base
        Contrat contratSauvegarde = contratRepository.save(contrat);


        //Envoi de Kafka notification
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setClientId(contrat.getClientId().toString());
        notificationMessage.setContractId(contrat.getId().toString());
        notificationMessage.setOperation("CREATION");
        notificationMessage.setMessage("Creation de pour le client " + clientDTO.getNom());

        kafkaProducer.sendNotification(notificationMessage);




        // Retourner en DTO
        return contratMapper.toContratDTO(contratSauvegarde);
    }

    public Optional<ContratDTO> obtenirContratParId(Long id) {
        return contratRepository.findById(id)
                .map(contratMapper::toContratDTO);
    }

    public ContratDTO mettreAJourContrat(Long id, ContratDTO updatedContratDTO) {
        // Vérifier si le client existe
        ClientDTO clientDTO = clientServiceClient.getClientById(updatedContratDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Le client n'existe pas."));
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable."));

        // Mise à jour des champs
        contrat.setDateDebut(updatedContratDTO.getDateDebut());
        contrat.setDateFin(updatedContratDTO.getDateFin());
        contrat.setMontantAssure(updatedContratDTO.getMontantAssure());
        contrat.setDureeEnAnnees(updatedContratDTO.getDureeEnAnnees());
        contrat.setEstValide(updatedContratDTO.isEstValide());



        // Sauvegarder
        Contrat contratMisAJour = contratRepository.save(contrat);

        //Envoi de Kafka notification
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setClientId(contrat.getClientId().toString());
        notificationMessage.setContractId(contrat.getId().toString());
        notificationMessage.setOperation("MISE A JOUR");
        notificationMessage.setMessage("Contrat de " + clientDTO.getNom() + " mise à jour " );

        kafkaProducer.sendNotification(notificationMessage);

        // Retourner en DTO
        return contratMapper.toContratDTO(contratMisAJour);
    }

    public void supprimerContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable.");
        }
        contratRepository.deleteById(id);
    }
}