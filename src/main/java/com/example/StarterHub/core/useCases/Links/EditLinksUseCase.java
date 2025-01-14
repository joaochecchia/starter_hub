package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;

import java.util.Optional;
import java.util.UUID;

public interface EditLinksUseCase {
    Optional<Links> execute(UUID id, Links links);
}
