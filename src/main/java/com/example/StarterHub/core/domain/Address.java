package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Address(
        UUID id,
        String country,
        String postal_code,
        String location,
        UserProperties userProperties
) { }
