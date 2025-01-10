package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Users(
        UUID id,
        String username,
        String password,
        String email,
        String phone,
        UserProperties userProperties
) { }
