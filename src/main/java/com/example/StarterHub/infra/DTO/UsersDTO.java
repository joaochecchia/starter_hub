package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.UUID;

public record UsersDTO(
        UUID id,
        String username,
        String password,
        String email,
        String phone,
        UserPropertiesDTO userPropertiesDTO
) { }
