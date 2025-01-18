package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.gateway.CommitGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchCommitUseCaseImp implements SearchCommitUseCase{

    private final CommitGateway commitGateway;

    public SearchCommitUseCaseImp(CommitGateway commitGateway) {
        this.commitGateway = commitGateway;
    }

    @Override
    public Optional<Commit> execute(UUID id) {
        return commitGateway.searchCommit(id);
    }
}
