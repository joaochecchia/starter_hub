package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.FoldersGateway;
import com.example.StarterHub.core.useCases.Folder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FolderConfigurator {

    @Bean
    public PostFolderUseCase postFolder(FoldersGateway foldersGateway){
        return new PostFolderUseCaseImp(foldersGateway);
    }

    @Bean
    public SaveAllFoldersUseCase saveAllFolders(FoldersGateway foldersGateway){
        return new SaveAllFoldersUseCaseImp(foldersGateway);
    }

    @Bean
    public FindAllFoldersUseCase findAllFolders(FoldersGateway foldersGateway){
        return new FindAllFoldersUseCaseImp(foldersGateway);
    }

    @Bean
    public SearchFolderUseCase searchFolder(FoldersGateway foldersGateway){
        return new SearchFolderUseCaseImp(foldersGateway);
    }

    @Bean
    public EditFolderUseCase editFolder(FoldersGateway foldersGateway){
        return new EditFolderUseCaseImp(foldersGateway);
    }

    @Bean
    public DeleteFolderUseCase deleteFolder(FoldersGateway foldersGateway){
        return new DeleteFolderUseCaseImp(foldersGateway);
    }
}
