package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.Optional;
import java.util.UUID;

public class SaveAllFoldersUseCaseImp implements SaveAllFoldersUseCase{

    private final FoldersGateway foldersGateway;

    public SaveAllFoldersUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public Optional<Folder> execute(Folder folder) {
        return foldersGateway.saveAllFolders(folder);
    }
}
