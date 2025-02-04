package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface  UserPropertiesRepository extends JpaRepository<UserPropertiesModel, UUID> {
    Optional<UserPropertiesModel> findByUserModelId(UUID id);
}
