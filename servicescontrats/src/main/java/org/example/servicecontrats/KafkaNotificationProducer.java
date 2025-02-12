package org.example.servicecontrats;

import org.example.servicenotifications.NotificationMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationProducer {

    private static final String TOPIC = "notifications";

    private  KafkaTemplate<String, NotificationMessage> notificationMessageKafkaTemplatekafkaTemplate;

    public KafkaNotificationProducer(KafkaTemplate<String, NotificationMessage> kafkaTemplate) {
        this.notificationMessageKafkaTemplatekafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationMessage message) {
        notificationMessageKafkaTemplatekafkaTemplate.send(TOPIC, message);
        System.out.println("Message envoyé : " + message);
    }

}
