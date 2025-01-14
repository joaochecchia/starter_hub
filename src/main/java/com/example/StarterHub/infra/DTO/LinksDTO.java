package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;

import java.util.UUID;

public record LinksDTO(
        UUID id,
        String link,
        UserPropertiesModel userProperties
) { }