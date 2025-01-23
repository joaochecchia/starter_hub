package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateLinksRequest(
        @NotEmpty(message = "Link is mandatory.") String link,
        @NotNull(message = "UserProperties ID is mandatory.") UUID userPropertiesID
) {
}
