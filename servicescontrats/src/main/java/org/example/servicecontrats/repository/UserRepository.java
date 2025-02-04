package org.example.servicecontrats.repository;


import org.example.servicecontrats.entity.UserContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserContrat,Long> {

    Optional<UserContrat> findByUsername(String username);
}