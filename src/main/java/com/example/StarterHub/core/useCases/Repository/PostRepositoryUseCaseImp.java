package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;

import java.util.Optional;
import java.util.UUID;

public class PostRepositoryUseCaseImp implements PostRepositoryUseCase {

    private final RepositoryGateway repositoryGateway;

    public PostRepositoryUseCaseImp(RepositoryGateway repositoryGateway) {
        this.repositoryGateway = repositoryGateway;
    }

    @Override
    public Optional<Repository> execute(Repository repository) {
        return repositoryGateway.postRepository(repository);
    }
}
