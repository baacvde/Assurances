package org.example.serviceclients.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.common.models.ClientDTO;
import org.example.serviceclients.entity.Client;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(ClientDTO clientDTO,Client client) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("no-reply@gmail.com");
            helper.setTo(clientDTO.getEmail());
            helper.setSubject("Information Client");

            String text = String.format(
                    "Bonjour %s, <br /> Votre identifiant client est %s. <br /> A bientot!",
                    clientDTO.getNom(),
                    client.getId()
            );

            helper.setText(text, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email notification", e);
        }
    }
}