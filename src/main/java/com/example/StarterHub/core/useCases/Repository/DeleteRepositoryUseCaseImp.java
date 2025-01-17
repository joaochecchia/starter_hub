package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.gateway.RepositoryGateway;

import java.util.UUID;

public class DeleteRepositoryUseCaseImp implements DeleteRepositoryUseCase{

    private final RepositoryGateway repositoryGateway;

    public DeleteRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public String execute(UUID id) {
        return repositoryGateway.deleteRepository(id);
    }
}
