package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;

import java.util.Optional;
import java.util.UUID;

public class SearchRepositoryUseCaseImp implements SearchRepositoryUseCase{
    @Override
    public Optional<Repository> execute(UUID repositoryId) {
        return Optional.empty();
    }
}
