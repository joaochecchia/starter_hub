package com.example.StarterHub.core.useCases.Artiffact;

import com.example.StarterHub.core.domain.Artifact;

import java.util.Optional;
import java.util.UUID;

public interface SearchArtiffactUseCase {
    Optional<Artifact> execute(UUID artifactId);
}
