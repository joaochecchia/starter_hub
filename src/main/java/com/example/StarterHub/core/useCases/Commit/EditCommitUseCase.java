package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;

import java.util.Optional;
import java.util.UUID;

public interface EditCommitUseCase {
    Optional<Commit> execute(UUID id, Commit commit);
}
