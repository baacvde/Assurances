package org.example.servicenotifications;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class KafkaNotificationConsumer {
    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void listen(String message) {
        // Traitement de la notification
        System.out.println("Notification received: " + message);
        // Ajouter ici la logique pour envoyer la notification par email ou autre
    }
}
