package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String Email);
    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
