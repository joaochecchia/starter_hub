package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;

import java.util.Optional;
import java.util.UUID;

public interface EditFolderUseCase {
    Optional<Folder> execute(UUID id, Folder folder);
}
