package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.Enums.Visibility;

import java.util.UUID;

public record RepositoryDTO(
        UUID id,
        String name,
        String repositoryDescription,
        Visibility visibility,
        UserPropertiesDTO userPropertiesDTO
) { }
