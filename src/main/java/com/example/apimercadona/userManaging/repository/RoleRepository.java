package com.example.apimercadona.userManaging.repository;

import com.example.apimercadona.entity.Role;
import com.example.apimercadona.entity.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(TypeRole name);
}
