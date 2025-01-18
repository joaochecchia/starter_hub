package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;

import java.util.Optional;

public interface PostCommitUseCase {
    Optional<Commit> execute(Commit commit);
}
