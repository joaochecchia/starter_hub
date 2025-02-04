package com.example.StarterHub.infra.requests.create;

import com.example.StarterHub.core.Enums.Visibility;
import com.example.StarterHub.core.domain.Folder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRepositoryRequest(
        @NotEmpty(message = "Name is mandatory.") String name,
        String repositoryDescription,
        @NotEmpty(message = "Visibility is mandatory.")Visibility visibility,
        Folder root,
        @NotNull(message = "UserProperties id is mandatory.") UUID userPropertiesID
) { }
