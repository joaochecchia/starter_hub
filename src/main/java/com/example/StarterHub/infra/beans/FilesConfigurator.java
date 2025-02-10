package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.FilesGatweay;
import com.example.StarterHub.core.useCases.Files.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class FilesConfigurator {

    @Bean
    public PostFilesUseCase postFile(FilesGatweay filesGatweay){
        return new PostFilesUseCaseImp(filesGatweay);
    }

    @Bean
    public SearchFilesUseCase searchFile(FilesGatweay filesGatweay){
        return new SearchFilesUseCaseImp(filesGatweay);
    }

    @Bean
    public EditFilesUseCase editFile(FilesGatweay filesGatweay){
        return new EditFilesUseCaseImp(filesGatweay);
    }

    @Bean
    public DeleteFilesUseCase deleteFile(FilesGatweay filesGatweay){
        return new DeleteFilesUseCaseImp(filesGatweay);
    }
}
