package org.example.servicecontrats.service;

import org.example.servicecontrats.service.NotificationService;
import org.example.servicecontrats.KafkaNotificationProducer;
import org.example.servicecontrats.entity.Contrat;
import org.example.servicecontrats.repository.ContratRepository;
import org.example.servicenotifications.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.serviceclients.mapper.ClientMapper;
import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.example.servicecontrats.feignclients.ClientServiceClient;
import org.example.servicecontrats.mappers.ContratMapper;


import java.util.Optional;


@Service
public class ContratService {

    private final NotificationService notificationService;
    private final KafkaNotificationProducer kafkaProducer;
    public final ContratRepository contratRepository;
    private final ClientServiceClient clientServiceClient;
    private final ContratMapper contratMapper;
    private final ClientMapper clientMapper;

    public ContratService(KafkaNotificationProducer kafkaProducer,
                          ContratRepository contratRepository,
                          ClientServiceClient clientServiceClient,
                          ContratMapper contratMapper,
                          ClientMapper clientMapper,
                          NotificationService notificationService) {
        this.kafkaProducer = kafkaProducer;
        this.contratRepository = contratRepository;
        this.clientServiceClient = clientServiceClient;
        this.contratMapper = contratMapper;
        this.clientMapper = clientMapper;
        this.notificationService = notificationService;
    }

    // ✅ Ajout de la méthode pour récupérer l'email du client à partir du contrat
    public String getClientEmailByContratId(Long contratId) {
        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));

        Long clientId = contrat.getClientId();

        Optional<ClientDTO> clientDTO = clientServiceClient.getClientById(clientId);

        return clientDTO.map(ClientDTO::getEmail)
                .orElseThrow(() -> new RuntimeException("Email du client introuvable"));
    }

    public ContratDTO creerContrat(ContratDTO contratDTO) {
        ClientDTO clientDTO = clientServiceClient.getClientById(contratDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Le client n'existe pas."));

        if (clientDTO.getAge() < 18) {
            throw new IllegalArgumentException("Le client doit avoir au moins 18 ans.");
        }
        if (contratDTO.getDureeEnAnnees() < 1) {
            throw new IllegalArgumentException("La durée du contrat doit être supérieure à 1 an.");
        }
        if (contratDTO.getMontantAssure() <= 10000) {
            throw new IllegalArgumentException("Le montant assuré doit être supérieur à 10 000 euros.");
        }

        Contrat contrat = contratMapper.toContrat(contratDTO);
        contrat.setClient(clientMapper.toClient(clientDTO));

        Contrat contratSauvegarde = contratRepository.save(contrat);

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setClientId(contrat.getClientId().toString());
        notificationMessage.setContractId(contrat.getId().toString());
        notificationMessage.setOperation("CREATION");
        notificationMessage.setMessage("Création de contrat pour le client " + clientDTO.getNom());

        kafkaProducer.sendNotification(notificationMessage);

        this.notificationService.sendMessage(contratDTO);

        return contratMapper.toContratDTO(contratSauvegarde);
    }

    public Optional<ContratDTO> obtenirContratParId(Long id) {
        return contratRepository.findById(id)
                .map(contratMapper::toContratDTO);
    }

    public ContratDTO mettreAJourContrat(Long id, ContratDTO updatedContratDTO) {
        ClientDTO clientDTO = clientServiceClient.getClientById(updatedContratDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Le client n'existe pas."));

        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable."));

        contrat.setDateDebut(updatedContratDTO.getDateDebut());
        contrat.setDateFin(updatedContratDTO.getDateFin());
        contrat.setMontantAssure(updatedContratDTO.getMontantAssure());
        contrat.setDureeEnAnnees(updatedContratDTO.getDureeEnAnnees());
        contrat.setEstValide(updatedContratDTO.isEstValide());

        Contrat contratMisAJour = contratRepository.save(contrat);

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setClientId(contrat.getClientId().toString());
        notificationMessage.setContractId(contrat.getId().toString());
        notificationMessage.setOperation("MISE A JOUR");
        notificationMessage.setMessage("Contrat de " + clientDTO.getNom() + " mis à jour.");

        kafkaProducer.sendNotification(notificationMessage);

        return contratMapper.toContratDTO(contratMisAJour);
    }

    public void supprimerContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable.");
        }
        contratRepository.deleteById(id);
    }
}
