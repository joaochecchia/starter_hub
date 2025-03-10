package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface SearchLinksUseCase {
    Optional<Links> execute(UUID userId);
}
