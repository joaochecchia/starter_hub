package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateFolderRequest(
        @NotEmpty(message = "Name is mandatory.") String name,
        UUID fatherID,
        UUID repositoryId
) { }