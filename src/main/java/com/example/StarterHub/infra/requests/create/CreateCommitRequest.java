package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCommitRequest(
        @NotEmpty(message = "Description is mandatory") String description,
        @NotNull(message = "Respository id is mandatory") UUID repositoryId
) {
}
