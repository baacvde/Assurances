package org.example.servicecontrats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    /**
     * Récupérer tous les contrats d'un client spécifique.
     *
     * @param clientId L'ID du client.
     * @return Une liste de contrats associés au client.
     */
    List<Contrat> findByClient_Id(Long clientId);
}
