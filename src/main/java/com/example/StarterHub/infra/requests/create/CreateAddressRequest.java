package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAddressRequest(
        String country,
        String postalCode,
        String location,
        @NotNull(message = "UserProperties id is  mandatory") UUID userPropertiesID
) { }
