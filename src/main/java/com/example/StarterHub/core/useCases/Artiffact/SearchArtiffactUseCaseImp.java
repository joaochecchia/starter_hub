package com.example.StarterHub.core.useCases.Artiffact;

import com.example.StarterHub.core.domain.Files;

import java.util.Optional;
import java.util.UUID;

public class SearchArtiffactUseCaseImp implements SearchArtiffactUseCase{

    @Override
    public Optional<Files> execute(UUID artifactId) {
        return Optional.empty();
    }
}
