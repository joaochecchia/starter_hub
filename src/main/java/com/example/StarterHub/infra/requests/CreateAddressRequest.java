package com.example.StarterHub.infra.requests;

import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateAddressRequest(
        String country,
        String postalCode,
        String location,
        @NotEmpty(message = "UserProperties id is  mandatory") UUID userPropertiesID
) { }
