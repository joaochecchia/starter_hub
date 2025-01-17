package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;

import java.util.Optional;
import java.util.UUID;

public interface EditRepositoryUseCase {
    Optional<Repository> execute(UUID id, Repository repository);
}
