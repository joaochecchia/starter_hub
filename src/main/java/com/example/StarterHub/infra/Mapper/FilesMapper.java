package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.requests.create.CreateFileRequest;
import org.springframework.stereotype.Component;

@Component
public class FilesMapper {
    public FilesModel toEntity(Files domain){
        return new FilesModel(
                domain.id(),
                domain.content(),
                new FolderModel(
                        domain.folderId()
                ),
                new CommitsModel(
                        domain.commitId()
                )
        );
    }

    public Files toEntity(CreateFileRequest request){
        return new Files(
                null,
                request.content(),
                request.folderId(),
                request.commitId()
        );
    }

    public Files toDomain(FilesModel model){
        return new Files(
                model.getId(),
                model.getContent(),
                model.getFolderModel().getId(),
                model.getCommitsModel().getHash()
        );
    }
}
