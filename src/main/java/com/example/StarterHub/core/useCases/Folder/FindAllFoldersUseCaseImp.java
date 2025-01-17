package com.example.StarterHub.core.useCases.Folder;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class FindAllFoldersUseCaseImp implements FindAllFoldersUseCase{

    private final FoldersGateway foldersGateway;

    public FindAllFoldersUseCaseImp(FoldersGateway foldersGateway) {
        this.foldersGateway = foldersGateway;
    }

    @Override
    public Optional<ArrayList<Folder>> execute(UUID id) {
        return foldersGateway.findAllFolders(id);
    }
}
