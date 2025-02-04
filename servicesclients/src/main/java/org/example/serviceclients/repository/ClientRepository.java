package org.example.serviceclients.repository;



import org.example.serviceclients.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Méthode  pour vérifier si un e-mail existe
    boolean existsByEmail(String email);
}

