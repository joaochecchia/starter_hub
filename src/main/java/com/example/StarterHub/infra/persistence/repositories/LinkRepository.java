package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.LinkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<LinkModel, UUID> {
    Optional<ArrayList<LinkModel>> findAllByUserPropertiesModelId(UUID id);
    Optional<LinkModel> findByLink(String link);
}
