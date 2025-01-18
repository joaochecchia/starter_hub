package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    private final FolderMapper folderMapper;

    public RepositoryMapper(FolderMapper folderMapper) {
        this.folderMapper = folderMapper;
    }

    public RepositoryModel toEntity(Repository domain){
        FolderModel rootModel = folderMapper.toEntity(domain.root());

        return new RepositoryModel(
                domain.id(),
                domain.name(),
                domain.repositoryDescription(),
                domain.visibility(),
                domain.creationTimeStamp(),
                domain.updateTimeStamp(),
                null,
                rootModel,
                new UserPropertiesModel(
                        domain.userPropertiesID()
                )
        );
    }

    public Repository toDomain(RepositoryModel model){
        Folder rootDomain = folderMapper.toDomain(model.getRoot());

        return new Repository(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getVisibility(),
                model.getCreationTimeStamp(),
                model.getUpdateTimeStamp(),
                null,
                rootDomain,
                model.getUserPropertiesModel().getId()
        );
    }

}
