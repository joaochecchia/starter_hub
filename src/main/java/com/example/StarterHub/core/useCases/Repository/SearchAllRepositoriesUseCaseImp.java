package com.example.StarterHub.core.useCases.Repository;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.useCases.Commit.SearchAllCommitsUseCase;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class SearchAllRepositoriesUseCaseImp implements SearchAllCommitsUseCase {
    @Override
    public Optional<ArrayList<Commit>> execute(UUID repositoryID) {
        return Optional.empty();
    }
}
