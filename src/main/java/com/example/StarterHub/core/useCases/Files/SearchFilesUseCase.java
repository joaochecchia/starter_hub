package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;

import java.util.Optional;
import java.util.UUID;

public interface SearchFilesUseCase {
    Optional<Files> execute(UUID id);
}
