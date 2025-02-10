package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import com.example.StarterHub.infra.requests.create.CreateRepositoryRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class RepositoryMapper {

    private final FolderMapper folderMapper;
    private final CommitMapper commitMapper;

    public RepositoryMapper(FolderMapper folderMapper, CommitMapper commitMapper) {
        this.folderMapper = folderMapper;
        this.commitMapper = commitMapper;
    }

    public RepositoryModel toEntity(Repository domain){
        FolderModel rootModel = domain.root() == null ? null : folderMapper.toEntity(domain.root());

        ArrayList<CommitsModel> commitsModel = domain.commits() == null ? null : domain.commits().stream()
                .map(commitMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        return new RepositoryModel(
                domain.id(),
                domain.name(),
                domain.repositoryDescription(),
                domain.visibility(),
                domain.localRepositoryPath(),
                domain.creationTimeStamp(),
                domain.updateTimeStamp(),
                commitsModel,
                rootModel,
                new UserPropertiesModel(
                        domain.userPropertiesID()
                )
        );
    }

    public Repository toDomain(CreateRepositoryRequest request){
        return new Repository(
                null,
                request.name(),
                request.repositoryDescription(),
                request.visibility(),
                request.localRepositoryPath(),
                null,
                null,
                new ArrayList<>(),
                null,
                request.userPropertiesID()
        );
    }

    public Repository toDomain(RepositoryModel model){
        Folder rootDomain = folderMapper.toDomain(model.getRoot());

        ArrayList<Commit> commitsDomain = model.getCommitsModel() == null ? null : model.getCommitsModel().stream()
                .map(commitMapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));

        return new Repository(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getVisibility(),
                model.getLocalRepositoryPath(),
                model.getCreationTimeStamp(),
                model.getUpdateTimeStamp(),
                commitsDomain,
                rootDomain,
                model.getUserPropertiesModel().getId()
        );
    }

}
