package com.example.StarterHub.infra.requests;

import com.example.StarterHub.core.Enums.Visibility;
import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.domain.Folder;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public record CreateRepositoryRequest(
        @NotEmpty(message = "Name is mandatory.") String name,
        String repositoryDescription,
        @NotEmpty(message = "Visibility is mandatory.")Visibility visibility,
        Folder root,
        @NotEmpty(message = "UserProperties id is mandatory.") UUID userPropertiesID
) { }
