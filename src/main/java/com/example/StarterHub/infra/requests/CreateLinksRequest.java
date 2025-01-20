package com.example.StarterHub.infra.requests;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateLinksRequest(
        @NotEmpty(message = "Link is mandatory.") String link,
        @NotEmpty(message = "UserProperties ID is mandatory.") UUID userPropertiesID
) {
}
