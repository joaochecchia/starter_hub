package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;

import java.util.Optional;
import java.util.UUID;

public class EditRepositoryUseCaseImp implements EditRepositoryUseCase{

    private final RepositoryGateway repositoryGateway;

    public EditRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<Repository> execute(UUID id, Repository repository) {
        return repositoryGateway.editRepository(id, repository);
    }
}
