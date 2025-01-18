package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface CommitsRepository extends JpaRepository<CommitsModel, UUID> {
    Optional<ArrayList<CommitsModel>> findAllByRepositoryModelId(UUID id);
}
