package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String country,
        String postalCode,
        String location,
        UserPropertiesModel userPropertiesModel
) { }

