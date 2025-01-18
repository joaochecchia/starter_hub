package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.CommitGateway;

import java.util.Optional;
import java.util.UUID;

public class EditCommitUseCaseImp implements EditCommitUseCase{

    private final CommitGateway commitGateway;

    public EditCommitUseCaseImp(CommitGateway commitGateway) {
        this.commitGateway = commitGateway;
    }

    @Override
    public Optional<Commit> execute(UUID id, Commit commit) {
        return commitGateway.editCommit(id, commit);
    }
}
