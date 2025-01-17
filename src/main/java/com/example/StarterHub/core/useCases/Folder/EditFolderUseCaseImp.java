package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.Optional;
import java.util.UUID;

public class EditFolderUseCaseImp implements EditFolderUseCase{

    private final FoldersGateway foldersGateway;

    public EditFolderUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public Optional<Folder> execute(UUID id, Folder folder) {
        return foldersGateway.editFolder(id, folder);
    }
}
