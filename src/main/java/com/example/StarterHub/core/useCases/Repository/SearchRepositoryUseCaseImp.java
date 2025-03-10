package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchRepositoryUseCaseImp implements SearchRepositoryUseCase{

    private final RepositoryGateway repositoryGateway;

    public SearchRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<Repository> execute(UUID id) {
        return repositoryGateway.searchRepository(id);
    }
}
