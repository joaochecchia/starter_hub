package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.gateway.CommitGateway;

import java.util.Optional;

public class PostCommitUseCaseImp implements PostCommitUseCase{

    private final CommitGateway commitGateway;

    public PostCommitUseCaseImp(CommitGateway commitGateway) {
        this.commitGateway = commitGateway;
    }


    @Override
    public Optional<Commit> execute(Commit commit) {
        return commitGateway.postCommit(commit);
    }
}
