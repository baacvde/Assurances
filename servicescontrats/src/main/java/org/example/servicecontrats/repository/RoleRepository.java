package org.example.servicecontrats.repository;

import org.example.servicecontrats.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}