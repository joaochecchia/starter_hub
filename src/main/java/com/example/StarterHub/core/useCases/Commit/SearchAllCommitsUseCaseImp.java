package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class SearchAllCommitsUseCaseImp implements SearchAllCommitsUseCase{
    @Override
    public Optional<ArrayList<Commit>> execute(UUID repositoryID) {
        return Optional.empty();
    }
}
