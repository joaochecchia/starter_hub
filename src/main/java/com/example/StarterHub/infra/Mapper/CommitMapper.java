package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.requests.create.CreateCommitRequest;
import org.springframework.stereotype.Component;

@Component
public class CommitMapper {
    public CommitsModel toEntity(Commit domain){
        return new CommitsModel(
                domain.hash(),
                domain.description(),
                domain.creationTimeStamp(),
                new RepositoryModel(
                        domain.repositoryId()
                )
        );
    }

    public Commit toDomain(CreateCommitRequest request){
        return new Commit(
                null,
                request.description(),
                null,
                null,
                request.repositoryId()
        );
    }

    public Commit toDomain(CommitsModel model){
        return new Commit(
                model.getHash(),
                model.getDescription(),
                model.getCreationTimeStamp(),
                null,
                model.getRepositoryModel().getId()
        );
    }
}
