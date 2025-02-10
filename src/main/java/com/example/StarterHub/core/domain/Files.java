package com.example.StarterHub.core.domain;

import java.util.UUID;

public record Files(
        UUID id,
        byte[] content,
        UUID folderId
) { }
