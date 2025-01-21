package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.requests.CreateFolderRequest;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FolderMapper {
    public Folder toDomain(FolderModel entity) {
        if (entity == null) {
            return null;
        }

        ArrayList<Folder> children = entity.getChildren() == null ? null : entity.getChildren().stream()
                .map(this::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));

        return new Folder(
                entity.getId(),
                entity.getName(),
                entity.getFather() != null ? entity.getFather().getId() : null,
                null,
                children,
                entity.getRepository() != null ? entity.getRepository().getId() : null
        );
    }

    public Folder toDomain(CreateFolderRequest request){
        return new Folder(
                null,
                request.name(),
                request.fatherID() != null ? request.fatherID() : null,
                null,
                null,
                request.repositoryId()
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
