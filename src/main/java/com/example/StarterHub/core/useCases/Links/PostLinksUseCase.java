package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;

import java.util.Optional;

public interface PostLinksUseCase {
    Optional<Links> execute(Links links);
}
