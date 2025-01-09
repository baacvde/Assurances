package org.example.servicecontrats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.serviceclients.mapper.ClientMapper;
import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.example.servicecontrats.feignclients.ClientServiceClient;
import org.example.servicecontrats.mappers.ContratMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContratService {


    private  final ContratRepository contratRepository;

    private final ClientServiceClient clientServiceClient;

    private final ContratMapper contratMapper;

    private final ClientMapper clientMapper;



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

        // Retourner en DTO
        return contratMapper.toContratDTO(contratSauvegarde);
    }

    public Optional<ContratDTO> obtenirContratParId(Long id) {
        return contratRepository.findById(id)
                .map(contratMapper::toContratDTO);
    }

    public ContratDTO mettreAJourContrat(Long id, ContratDTO updatedContratDTO) {
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
