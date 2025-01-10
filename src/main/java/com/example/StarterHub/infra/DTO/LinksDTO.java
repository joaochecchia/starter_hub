package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.UUID;

public record LinksDTO(
        UUID id,
        String link,
        UserPropertiesDTO userPropertiesDTO
) { }