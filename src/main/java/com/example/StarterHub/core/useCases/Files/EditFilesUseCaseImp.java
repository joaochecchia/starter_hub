package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.gateway.FilesGatweay;

import java.util.Optional;
import java.util.UUID;

public class EditFilesUseCaseImp implements EditFilesUseCase{

    private final FilesGatweay filesGatweay;

    public EditFilesUseCaseImp(FilesGatweay filesGatweay) {
        this.filesGatweay = filesGatweay;
    }

    @Override
    public Optional<Files> execute(UUID id, Files files) {
        return filesGatweay.editFile(id, files);
    }
}
