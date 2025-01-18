package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Commit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface CommitGateway {
    Optional<Commit> postCommit(Commit folder);
    Optional<Commit> searchCommit(UUID id);
    Optional<ArrayList<Commit>> findAllCommits(UUID id);
    Optional<Commit> editCommit(UUID id, Commit folder);
    String deleteCommit(UUID id);
}
