package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryGateway {
    Optional<Repository> postRepository(Repository repository);
    Optional<Repository> searchRepository(UUID id);
    Optional<ArrayList<Repository>> findAllRepositories(UUID id);
    Optional<Repository> editRepository(UUID id, Repository repository);
    String deleteRepository(UUID id);
    boolean repositoryExists(UUID id, String name);
}
