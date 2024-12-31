package org.example.servicenotifications;

import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.springframework.stereotype.Service;
import org.example.common.models.NotificationRequest;

@Service
public class NotificationService {

    /**
     * Méthode pour envoyer une notification.
     */
    public void envoyerNotification(NotificationRequest request) {

        System.out.println("Notification envoyée !");

        // Accéder aux informations du client via le DTO ClientDTO
        ClientDTO client = request.getClient();
        System.out.println("Client ID : " + client.getId());
        System.out.println("Client Nom : " + client.getNom());
        System.out.println("Client Email : " + client.getEmail());

        // Accéder aux informations du contrat via le DTO ContratDTO
        ContratDTO contrat = request.getContrat();
        System.out.println("Contrat ID : " + contrat.getId());
        System.out.println("Montant Assuré : " + contrat.getMontantAssure());
        System.out.println("Durée du Contrat : " + contrat.getDureeEnAnnees());

        // Informations sur l'opération et le message
        System.out.println("Type d'Opération : " + request.getTypeOperation());
        System.out.println("Message : " + request.getMessage());
    }
}

