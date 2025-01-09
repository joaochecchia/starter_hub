package com.example.StarterHub.core.useCases.Links;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface SearchLinksUseCase {
    Optional<ArrayList<String>> execute(UUID userId);
}
