package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.gateway.FilesGatweay;

import java.util.Optional;
import java.util.UUID;

public class SearchFilesUseCaseImp implements SearchFilesUseCase {

    private final FilesGatweay filesGatweay;

    public SearchFilesUseCaseImp(FilesGatweay filesGatweay) {
        this.filesGatweay = filesGatweay;
    }

    @Override
    public Optional<Files> execute(UUID id) {
        return filesGatweay.searchFile(id);
    }
}
