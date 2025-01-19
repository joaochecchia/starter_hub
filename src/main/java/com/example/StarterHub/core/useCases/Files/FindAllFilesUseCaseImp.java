package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.gateway.FilesGatweay;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class FindAllFilesUseCaseImp implements FindAllFilesUseCase{

    private final FilesGatweay filesGatweay;

    public FindAllFilesUseCaseImp(FilesGatweay filesGatweay) {
        this.filesGatweay = filesGatweay;
    }

    @Override
    public Optional<ArrayList<Files>> execute(UUID id) {
        return filesGatweay.findAllFiles(id);
    }
}
