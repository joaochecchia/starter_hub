package com.example.StarterHub.core.useCases.Commit;

import com.example.StarterHub.core.domain.Commit;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface SearchAllCommitsUseCase {
    Optional<ArrayList<Commit>> execute(UUID id);
}
