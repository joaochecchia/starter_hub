package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class FindAllRepositoriesUseCaseImp implements FindAllRepositoriesUseCase {

    private final RepositoryGateway repositoryGateway;

    public FindAllRepositoriesUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<ArrayList<Repository>> execute(UUID id) {
        return repositoryGateway.findAllRepositories(id);
    }
}
