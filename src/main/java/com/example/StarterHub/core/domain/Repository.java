package com.example.StarterHub.core.domain;

import com.example.StarterHub.core.Enums.Visibility;

import java.util.UUID;

public record Repository(
        UUID id,
        String name,
        String repositoryDescription,
        Visibility visibility,
        UUID userPropertiesID
) { }
