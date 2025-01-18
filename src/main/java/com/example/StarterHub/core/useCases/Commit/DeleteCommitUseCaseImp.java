package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.gateway.CommitGateway;

import java.util.UUID;

public class DeleteCommitUseCaseImp implements DeleteCommitUseCase{

    private final CommitGateway commitGateway;

    public DeleteCommitUseCaseImp(CommitGateway commitGateway) {
        this.commitGateway = commitGateway;
    }

    @Override
    public String execute(UUID id) {
        return commitGateway.deleteCommit(id);
    }
}
