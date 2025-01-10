package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String country,
        String postal_code,
        String location,
        UserPropertiesDTO userPropertiesDTO
) { }

