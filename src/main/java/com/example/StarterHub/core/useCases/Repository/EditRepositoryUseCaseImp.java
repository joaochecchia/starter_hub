package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;
import com.example.StarterHub.infra.exceptions.CredentialsAreadyExistsExceptions;

import java.util.Optional;
import java.util.UUID;

public class EditRepositoryUseCaseImp implements EditRepositoryUseCase{

    private final RepositoryGateway repositoryGateway;

    public EditRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<Repository> execute(UUID id, Repository repository) {
        if (repositoryGateway.repositoryExists(repository.userPropertiesID(), repository.name())){
            throw new CredentialsAreadyExistsExceptions("You already have another repository with " + repository.name() + " name.");
        }

        return repositoryGateway.editRepository(id, repository);
    }
}
