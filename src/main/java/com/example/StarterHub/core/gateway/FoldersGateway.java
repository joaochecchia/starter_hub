package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Folder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FoldersGateway {
    Optional<Folder> postFolders(Folder folder);
    Optional<Folder> saveAllFolders(Folder folder);
    Optional<Folder> searchFolders(UUID id);
    Optional<ArrayList<Folder>> findAllFolders(UUID id);
    Optional<Folder> editFolder(UUID id, Folder folder);
    String deleteFolder(UUID id);
}
