package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;

import java.util.Optional;

public interface PostFilesUseCase {
    Optional<Files> execute(Files files);
}
