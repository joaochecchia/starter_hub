package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateUserPropertiesRequest(
        String description,
        String encodedPhoto,
        String company,
        @NotNull(message = "FK is mandatory") UUID usersId
) { }
