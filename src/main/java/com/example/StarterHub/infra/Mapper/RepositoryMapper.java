package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    public RepositoryModel toEntity(Repository domain){
        return new RepositoryModel(
                domain.id(),
                domain.name(),
                domain.repositoryDescription(),
                domain.visibility(),
                domain.creationTimeStamp(),
                domain.updateTimeStamp(),
                null,
                null,
                new UserPropertiesModel(
                        domain.userPropertiesID()
                )
        );
    }

    public Repository toDomain(RepositoryModel model){
        return new Repository(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getVisibility(),
                model.getCreationTimeStamp(),
                model.getUpdateTimeStamp(),
                null,
                null,
                model.getUserPropertiesModel().getId()
        );
    }

}
