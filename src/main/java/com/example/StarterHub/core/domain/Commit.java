package com.example.StarterHub.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public record Commit(
        UUID hash,
        String description,
        LocalDateTime creationTimeStamp,
        ArrayList<Files> files,
        UUID repositoryId
) { }
