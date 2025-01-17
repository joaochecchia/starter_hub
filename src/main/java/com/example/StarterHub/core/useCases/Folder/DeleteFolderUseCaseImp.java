package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.UUID;

public class DeleteFolderUseCaseImp implements DeleteFolderUseCase{

    private final FoldersGateway foldersGateway;

    public DeleteFolderUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public String execute(UUID id) {
        return foldersGateway.deleteFolder(id);
    }
}
