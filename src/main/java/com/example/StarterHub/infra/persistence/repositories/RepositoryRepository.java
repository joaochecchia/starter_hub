package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryRepository extends JpaRepository<RepositoryModel, UUID> {
    Optional<ArrayList<RepositoryModel>> findAllByUserPropertiesModelId(UUID id);
}
