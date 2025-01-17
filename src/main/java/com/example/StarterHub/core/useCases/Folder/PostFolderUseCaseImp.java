package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.Optional;

public class PostFolderUseCaseImp implements PostFolderUseCase{

    private final FoldersGateway foldersGateway;

    public PostFolderUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public Optional<Folder> execute(Folder folder) {
        return foldersGateway.postFolders(folder);
    }
}
