package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FindAllFilesUseCase {
    Optional<ArrayList<Files>> execute(UUID id);
}
