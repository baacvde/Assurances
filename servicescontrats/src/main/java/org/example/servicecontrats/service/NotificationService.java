package org.example.servicecontrats.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.example.serviceclients.entity.Client;
import org.example.servicecontrats.entity.Contrat;
import org.example.servicecontrats.feignclients.ClientServiceClient;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;
    private final ClientServiceClient clientServiceClient;

    public NotificationService(JavaMailSender javaMailSender, ClientServiceClient clientServiceClient) {
        this.javaMailSender = javaMailSender;
        this.clientServiceClient = clientServiceClient;
    }

    public void sendMessage(ContratDTO contratDTO) {
        try {
            // Récupérer le client depuis le service client
            ClientDTO clientDTO = clientServiceClient.getClientById(contratDTO.getClientId())
                    .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // Définir l'encodage UTF-8

            helper.setFrom("no-reply@gmail.com");
            helper.setTo(clientDTO.getEmail());
            helper.setSubject("Information sur votre contrat");

            String text = String.format(
                    """
                    <html>
                    <body>
                        <p>Bonjour %s,</p>
                        <p>Nous vous informons que votre contrat est bien enregistré. Voici les détails :</p>
                        <ul>
                            <li><b>Identifiant client :</b> %s</li>
                            <li><b>Durée du contrat :</b> %d années</li>
                            <li><b>Montant assuré :</b> %.2f €</li>
                            <li><b>Date de fin :</b> %s</li>
                        </ul>
                        <p>Merci de votre confiance.</p>
                        <p>À bientôt !</p>
                    </body>
                    </html>
                    """,
                    clientDTO.getNom(),
                    clientDTO.getId(),
                    contratDTO.getDureeEnAnnees(),
                    contratDTO.getMontantAssure(),
                    contratDTO.getDateFin()
            );

            helper.setText(text, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Échec de l'envoi de l'email", e);
        }
    }

}
