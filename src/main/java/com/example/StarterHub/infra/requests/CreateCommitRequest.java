package com.example.StarterHub.infra.requests;

import com.example.StarterHub.core.domain.Files;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public record CreateCommitRequest(
        @NotEmpty(message = "Description is mandatory") String description,
        @NotEmpty(message = "Respository id is mandatory") UUID repositoryId
) {
}
