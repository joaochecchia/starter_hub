package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;
import com.example.StarterHub.infra.exceptions.CredentialsAreadyExistsExceptions;

import java.util.Optional;

public class PostRepositoryUseCaseImp implements PostRepositoryUseCase {

    private final RepositoryGateway repositoryGateway;

    public PostRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<Repository> execute(Repository repository) {
        if (repositoryGateway.repositoryExists(repository.userPropertiesID(), repository.name())){
            throw new CredentialsAreadyExistsExceptions("You already have another repository with " + repository.name() + " name.");
        }

        return repositoryGateway.postRepository(repository);
    }
}
