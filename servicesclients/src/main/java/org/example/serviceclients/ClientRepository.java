package org.example.serviceclients;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Méthode  pour vérifier si un e-mail existe
    boolean existsByEmail(String email);
}

