package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FindAllRepositoriesUseCase {
    Optional<ArrayList<Repository>> execute(UUID repositoryId);
}
