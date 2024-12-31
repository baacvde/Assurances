package org.example.serviceclients;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * Créer un nouveau client.
     */
    public Client creerClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Un client avec cet email existe déjà.");
        }
        return clientRepository.save(client);
    }

    /**
     * Obtenir un client par son ID.
     */
    public Optional<Client> obtenirClientParId(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Calculer l'âge d'un client à partir de sa date de naissance.
     */
    public int calculerAge(LocalDate dateDeNaissance) {
        return Period.between(dateDeNaissance, LocalDate.now()).getYears();
    }

    /**
     * Valider qu'un client a une adresse e-mail valide.
     */
    public boolean estEmailValide(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    /**
     * Supprimer un client par ID.
     */
    public void supprimerClient(Long id) {
        clientRepository.deleteById(id);
    }
}

