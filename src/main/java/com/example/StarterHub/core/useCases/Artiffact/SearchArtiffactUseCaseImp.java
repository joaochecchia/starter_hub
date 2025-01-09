package com.example.StarterHub.core.useCases.Artiffact;

import com.example.StarterHub.core.domain.Artifact;

import java.util.Optional;
import java.util.UUID;

public class SearchArtiffactUseCaseImp implements SearchArtiffactUseCase{

    @Override
    public Optional<Artifact> execute(UUID artifactId) {
        return Optional.empty();
    }
}
