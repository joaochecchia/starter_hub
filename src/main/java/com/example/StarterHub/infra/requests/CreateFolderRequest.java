package com.example.StarterHub.infra.requests;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.UUID;

public record CreateFolderRequest(
        @NotEmpty(message = "Name is mandatory.") String name,
        UUID fatherID,
        @NotEmpty(message = "repositoryId is mandatory.") UUID repositoryId
) { }