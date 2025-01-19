package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;

import java.util.Optional;
import java.util.UUID;

public class EditFilesUseCaseImp implements EditFilesUseCase{

    private final EditFilesUseCase editFilesUseCase;

    public EditFilesUseCaseImp(EditFilesUseCase editFilesUseCase) {
        this.editFilesUseCase = editFilesUseCase;
    }

    @Override
    public Optional<Files> execute(UUID id, Files files) {
        return editFilesUseCase.execute(id, files);
    }
}
