package org.example.servicecontrats;



import org.example.servicecontrats.feignclients.ClientServiceClient;
import org.example.common.models.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ClientServiceClient clientServiceClient;

    /**
     * Créer un contrat avec validation des règles métier.
     */
    public Contrat creerContrat(Contrat contrat) {
        // Vérifier si le client existe dans le Service des Clients
        ClientDTO client = clientServiceClient.getClientById(contrat.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Le client n'existe pas."));

        // Vérifier les règles métier
        if (client.getAge() < 18) {
            throw new IllegalArgumentException("Le client doit avoir au moins 18 ans.");
        }
        if (contrat.getDureeEnAnnees() < 1) {
            throw new IllegalArgumentException("La durée du contrat doit être supérieure à 1 an.");
        }
        if (contrat.getMontantAssure() <= 10000) {
            throw new IllegalArgumentException("Le montant assuré doit être supérieur à 10 000 euros.");
        }

        // Sauvegarder le contrat
        return contratRepository.save(contrat);
    }

    /**
     * Obtenir un contrat par ID.
     */
    public Optional<Contrat> obtenirContratParId(Long id) {
        return contratRepository.findById(id);
    }

    /**
     * Mettre à jour un contrat existant.
     */
    public Contrat mettreAJourContrat(Long id, Contrat updatedContrat) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable."));

        contrat.setDureeEnAnnees(updatedContrat.getDureeEnAnnees());
        contrat.setMontantAssure(updatedContrat.getMontantAssure());
        return contratRepository.save(contrat);
    }

    /**
     * Supprimer un contrat.
     */
    public void supprimerContrat(Long id) {
        contratRepository.deleteById(id);
    }
}
