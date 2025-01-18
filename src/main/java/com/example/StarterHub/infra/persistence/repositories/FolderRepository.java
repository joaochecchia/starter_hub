package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.FolderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FolderRepository extends JpaRepository<FolderModel, UUID> {
    Optional<ArrayList<FolderModel>> findAllByRepository_Id(UUID id);
}