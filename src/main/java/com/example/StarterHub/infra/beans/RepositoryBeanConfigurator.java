package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.RepositoryGateway;
import com.example.StarterHub.core.useCases.Repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeanConfigurator {

    @Bean
    public PostRepositoryUseCase postRepository(RepositoryGateway repositoryGateway){
        return new PostRepositoryUseCaseImp(repositoryGateway);
    }

    @Bean
    public SearchRepositoryUseCase searchRepository(RepositoryGateway repositoryGateway){
        return new SearchRepositoryUseCaseImp(repositoryGateway);
    }

    @Bean
    public FindAllRepositoriesUseCase findAllRepositories(RepositoryGateway repositoryGateway){
        return new FindAllRepositoriesUseCaseImp(repositoryGateway);
    }

    @Bean
    public EditRepositoryUseCase editRepository(RepositoryGateway repositoryGateway){
        return new EditRepositoryUseCaseImp(repositoryGateway);
    }

    @Bean
    public DeleteRepositoryUseCase deleteRepository(RepositoryGateway repositoryGateway){
        return new DeleteRepositoryUseCaseImp(repositoryGateway);
    }
}
