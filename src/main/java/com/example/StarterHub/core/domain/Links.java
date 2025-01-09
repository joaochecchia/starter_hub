package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Links(
        UUID id,
        String link,
        UserProperties userProperties
) { }
