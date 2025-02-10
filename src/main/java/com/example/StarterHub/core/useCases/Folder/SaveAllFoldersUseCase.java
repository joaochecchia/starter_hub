package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;

import java.util.Optional;
import java.util.UUID;

public interface SaveAllFoldersUseCase {
    Optional<Folder> execute(Folder folder);
}
