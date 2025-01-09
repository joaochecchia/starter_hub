package com.example.StarterHub.core.useCases.Changes;

import com.example.StarterHub.core.domain.Changes;

import java.util.Optional;
import java.util.UUID;

public interface SearchChangeUseCase {
    Optional<Changes> execute(UUID changesId);
}
