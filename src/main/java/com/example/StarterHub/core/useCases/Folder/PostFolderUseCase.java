package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;

import java.util.Optional;

public interface PostFolderUseCase {
    Optional<Folder> execute(Folder folder);
}
