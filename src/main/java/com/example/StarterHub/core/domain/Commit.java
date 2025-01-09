package com.example.StarterHub.core.domain;

import java.util.ArrayList;
import java.util.UUID;

public record Commit(
        UUID hash,
        String description,
        Repository repository,
        ArrayList<Changes> changes,
        ArrayList<Artifact> artifact
) { }
