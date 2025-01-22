package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateUserPropertiesRequest(
        String description,
        String encodedPhoto,
        String company,
        @NotEmpty(message = "FK is mandatory") UUID usersId
) { }
