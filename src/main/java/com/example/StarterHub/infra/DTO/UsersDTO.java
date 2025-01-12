package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;

import java.util.UUID;

public record UsersDTO(
        UUID id,
        String username,
        String password,
        String email,
        String phone,
        UserPropertiesModel userPropertiesModel
) { }
