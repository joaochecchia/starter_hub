package com.example.StarterHub.core.domain;

import java.util.ArrayList;
import java.util.UUID;

public record Commit(
        UUID hash,
        String description,
        ArrayList<Files> files,
        UUID repositoryId
) { }
