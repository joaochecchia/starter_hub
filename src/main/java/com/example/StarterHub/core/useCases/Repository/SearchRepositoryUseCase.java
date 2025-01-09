package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;

import java.util.Optional;
import java.util.UUID;

public interface SearchRepositoryUseCase {
    Optional<Repository> execute(UUID repositoryId);
}
