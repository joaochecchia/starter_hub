package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchFolderUseCaseImp implements SearchFolderUseCase{

    private final FoldersGateway foldersGateway;

    public SearchFolderUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public Optional<Folder> execute(UUID id) {
        return foldersGateway.searchFolders(id);
    }
}
