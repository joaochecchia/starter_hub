package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPropertiesRepository extends JpaRepository<UserPropertiesModel, UUID> {
}
