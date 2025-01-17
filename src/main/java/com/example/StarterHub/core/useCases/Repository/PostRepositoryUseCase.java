package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;

import java.util.Optional;

public interface PostRepositoryUseCase {
    Optional<Repository> execute(Repository repository);
}
