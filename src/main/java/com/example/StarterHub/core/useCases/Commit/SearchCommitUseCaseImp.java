package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;

import java.util.Optional;
import java.util.UUID;

public class SearchCommitUseCaseImp implements SearchCommitUseCase{
    @Override
    public Optional<Commit> execute(UUID repositoryID) {
        return Optional.empty();
    }
}
