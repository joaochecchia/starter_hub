package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.requests.create.CreateFileRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class FilesMapper {
    public FilesModel toEntity(Files domain){
        return new FilesModel(
                domain.id(),
                domain.content(),
                new FolderModel(
                        domain.folderId()
                )
        );
    }

    public FilesModel toEntity(Files domain, UUID folderID){
        return new FilesModel(
                domain.id(),
                domain.content(),
                new FolderModel(
                        folderID
                )
        );
    }

    public Files toDomain(CreateFileRequest request) throws IOException {
        return new Files(
                null,
                request.content() != null ? request.content().getBytes() : null,
                request.folderId()
        );
    }

    public Files toDomain(FilesModel model){
        return new Files(
                model.getId(),
                model.getContent(),
                model.getFolderModel().getId()
        );
    }
}
