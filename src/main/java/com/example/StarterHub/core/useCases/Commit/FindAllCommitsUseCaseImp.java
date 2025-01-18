package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.gateway.CommitGateway;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class FindAllCommitsUseCaseImp implements FindAllCommitsUseCase {

    private final CommitGateway commitGateway;

    public FindAllCommitsUseCaseImp(CommitGateway commitGateway) {
        this.commitGateway = commitGateway;
    }

    @Override
    public Optional<ArrayList<Commit>> execute(UUID id) {
        return commitGateway.findAllCommits(id);
    }
}
