package org.example.serviceclients.service;

import org.example.common.models.ClientDTO;
import org.example.serviceclients.repository.ClientRepository;
import org.example.serviceclients.entity.Client;
import org.example.serviceclients.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    public final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Autowired
    private  NotificationService notificationService;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }




    /**
     * Crée un nouveau client.
     *
     * @param clientDTO DTO du client à créer.
     * @return Le DTO du client sauvegardé.
     */
    public ClientDTO creerClient(ClientDTO clientDTO) {
        // Convertir le DTO en entité
        Client client = clientMapper.toClient(clientDTO);

        // Vérifier l'unicité de l'email
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Un client avec cet email existe déjà.");
        }

        // Sauvegarder l'entité
        Client savedClient = clientRepository.save(client);

        // Notification après insertion réussie
        this.notificationService.sendNotification(clientDTO,client);

        // Convertir l'entité sauvegardée en DTO
        return clientMapper.toClientDTO(savedClient);
    }

    /**
     * Obtenir un client par ID.
     *
     * @param id ID du client.
     * @return Le DTO du client correspondant.
     */
    public ClientDTO obtenirClientParId(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable."));

        return clientMapper.toClientDTO(client);
    }

    /**
     * Vérifie si un email est valide.
     *
     * @param email Email à valider.
     * @return true si l'email est valide, sinon false.
     */
    public boolean estEmailValide(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    /**
     * Supprime un client par ID.
     *
     * @param id ID du client à supprimer.
     */
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client introuvable avec l'ID : " + id);
        }
        clientRepository.deleteById(id);
    }

    /**
     * Met à jour un client existant.
     *
     * @param id        ID du client à mettre à jour.
     * @param clientDTO DTO contenant les nouvelles informations du client.
     * @return Le client mis à jour.
     */
    public ClientDTO mettreAJourClient(Long id, ClientDTO clientDTO) {
        Client clientExistant = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable avec l'ID : " + id));

        // Mapper les champs mis à jour
        clientMapper.updateClientFromDTO(clientDTO, clientExistant);

        // Sauvegarder les modifications
        Client clientMisAJour = clientRepository.save(clientExistant);

        return clientMapper.toClientDTO(clientMisAJour);
    }
    public List<ClientDTO> obtenirClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toClientDTO)
                .collect(Collectors.toList());
    }
}
