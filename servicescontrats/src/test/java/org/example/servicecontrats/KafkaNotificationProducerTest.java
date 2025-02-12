package org.example.servicecontrats;

import org.example.servicecontrats.KafkaNotificationProducer;
import org.example.servicenotifications.NotificationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

class KafkaNotificationProducerTest {

    @Mock
    private KafkaTemplate<String, NotificationMessage> kafkaTemplate;


    @InjectMocks
    private KafkaNotificationProducer producer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation de Mockito
    }

    @Test
    void sendNotificationTest() {
        NotificationMessage message = new NotificationMessage(
                "123", "456", "CREATE", "Contrat créé avec succès"
        );
        producer.sendNotification(message);
        verify(kafkaTemplate).send("notifications", message);
    }
}
