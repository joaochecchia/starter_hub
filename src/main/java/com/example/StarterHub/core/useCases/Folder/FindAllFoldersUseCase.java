package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FindAllFoldersUseCase {
    Optional<ArrayList<Folder>> execute(UUID id);
}
