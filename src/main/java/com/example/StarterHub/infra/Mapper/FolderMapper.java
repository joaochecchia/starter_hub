package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.requests.SaveAllFoldersRequest;
import com.example.StarterHub.infra.requests.create.CreateFileRequest;
import com.example.StarterHub.infra.requests.create.CreateFolderRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FolderMapper {
    private final FilesMapper filesMapper;

    public FolderMapper(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

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

    public Folder toDomainRecursive(SaveAllFoldersRequest request) throws IOException {
        ArrayList<Folder> children = new ArrayList<>();
        ArrayList<SaveAllFoldersRequest> folders = request.children();
        for(SaveAllFoldersRequest i : folders){
            children.add(toDomainRecursive(i));
        }

        ArrayList<Files> files = new ArrayList<>();
        for (CreateFileRequest createFileRequest : request.files()) {
            Files domain = filesMapper.toDomain(createFileRequest);
            files.add(domain);
        }

        return new Folder(
                null,
                request.name(),
                request.fatherID(),
                files,
                children,
                request.repositoryId()
        );
    }

    public Folder toDomainRecursive(FolderModel model){
        ArrayList<Folder> folder = new ArrayList<>();
        ArrayList<Files> files = new ArrayList<>();
        ArrayList<FolderModel> children = (ArrayList<FolderModel>) model.getChildren();

        if(children != null){
            for(FolderModel i : children){
                folder.add(toDomainRecursive(i));
            }
        }

        if(model.getFiles() != null){
            for(FilesModel i : model.getFiles()){
                files.add(filesMapper.toDomain(i));
            }
        }


        return new Folder(
                model.getId() != null ? model.getId() : null,
                model.getName(),
                model.getFather() != null ? model.getFather().getId() : null,
                files,
                folder,
                model.getRepository() != null ? model.getRepository().getId() : null
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
