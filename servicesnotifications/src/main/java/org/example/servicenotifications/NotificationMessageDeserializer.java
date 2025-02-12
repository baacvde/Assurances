package org.example.servicenotifications;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class NotificationMessageDeserializer implements Deserializer<NotificationMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration si nécessaire
    }

    @Override
    public NotificationMessage deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NotificationMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        // Nettoyage si nécessaire
    }
}

