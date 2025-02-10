package com.example.StarterHub.infra.persistence.repositories;

import com.example.StarterHub.infra.persistence.entities.FilesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FilesRepository extends JpaRepository<FilesModel, UUID> {
}
