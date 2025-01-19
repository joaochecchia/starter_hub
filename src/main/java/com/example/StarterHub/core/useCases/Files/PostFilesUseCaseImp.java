package com.example.StarterHub.core.useCases.Files;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.gateway.FilesGatweay;

import java.util.Optional;

public class PostFilesUseCaseImp implements PostFilesUseCase{

    private final FilesGatweay filesGatweay;

    public PostFilesUseCaseImp(FilesGatweay filesGatweay) {
        this.filesGatweay = filesGatweay;
    }

    @Override
    public Optional<Files> execute(Files files) {
        return filesGatweay.postFile(files);
    }
}
