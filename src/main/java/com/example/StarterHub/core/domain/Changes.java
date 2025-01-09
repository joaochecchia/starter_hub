package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Changes(
        UUID id,
        byte[] changes,
        Commit commit
) { }
