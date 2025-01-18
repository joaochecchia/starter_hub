package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class FolderMapper {
    public Folder toDomain(FolderModel entity) {
        if (entity == null) {
            return null;
        }
        return new Folder(
                entity.getId(),
                entity.getName(),
                entity.getFather() != null ? entity.getFather().getId() : null,
                null,
                null,
                entity.getRepository() != null ? entity.getRepository().getId() : null
        );
    }

    public FolderModel toEntity(Folder domain) {
        if (domain == null) {
            return null;
        }
        return new FolderModel(
                domain.id(),
                domain.name(),
                domain.fatherID() != null ? new FolderModel(domain.fatherID()) : null,
                domain.repositoryId() != null ? new RepositoryModel(domain.repositoryId()) : null
        );
    }
}
