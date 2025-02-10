package com.example.StarterHub.infra.requests;

import com.example.StarterHub.infra.requests.create.CreateFileRequest;

import java.util.ArrayList;
import java.util.UUID;

public record SaveAllFoldersRequest(
        String name,
        UUID fatherID,
        ArrayList<CreateFileRequest> files,
        ArrayList<SaveAllFoldersRequest> children,
        UUID repositoryId
) { }