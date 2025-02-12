package org.example.servicenotifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.time.LocalDateTime;


@Service
public class KafkaNotificationConsumer {

    private final NotificationRepository notificationRepository;

    public KafkaNotificationConsumer(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void consumeNotification(NotificationMessage message) {
        // Save to database
        Notification notification = new Notification();
        notification.setClientId(message.getClientId());
        notification.setContractId(message.getContractId());
        notification.setOperation(message.getOperation());
        notification.setMessage(message.getMessage());
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);

        System.out.println("Notification reçue : " + message);
    }

}
