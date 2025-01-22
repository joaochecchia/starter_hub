package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateAddressRequest(
        String country,
        String postalCode,
        String location,
        @NotEmpty(message = "UserProperties id is  mandatory") UUID userPropertiesID
) { }
