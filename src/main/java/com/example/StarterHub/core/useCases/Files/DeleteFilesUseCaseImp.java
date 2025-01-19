package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.gateway.FilesGatweay;

import java.util.UUID;

public class DeleteFilesUseCaseImp implements DeleteFilesUseCase{

    private final FilesGatweay filesGatweay;

    public DeleteFilesUseCaseImp(FilesGatweay filesGatweay) {
        this.filesGatweay = filesGatweay;
    }

    @Override
    public String execute(UUID id) {
        return filesGatweay.deleteFile(id);
    }
}
