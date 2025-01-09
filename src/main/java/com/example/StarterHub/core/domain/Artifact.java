package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Artifact(
        UUID id,
        byte[] artiffact,
        Commit commit
) { }
