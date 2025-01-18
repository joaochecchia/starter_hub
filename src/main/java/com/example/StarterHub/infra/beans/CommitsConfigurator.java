package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.CommitGateway;
import com.example.StarterHub.core.useCases.Commit.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommitsConfigurator {

    @Bean
    public PostCommitUseCase postCommit(CommitGateway commitGateway){
        return new PostCommitUseCaseImp(commitGateway);
    }

    @Bean
    public SearchCommitUseCase searchCommit(CommitGateway commitGateway){
        return new SearchCommitUseCaseImp(commitGateway);
    }

    @Bean
    public FindAllCommitsUseCase findAll(CommitGateway commitGateway){
        return new FindAllCommitsUseCaseImp(commitGateway);
    }

    @Bean
    public EditCommitUseCase editCommit(CommitGateway commitGateway){
        return new EditCommitUseCaseImp(commitGateway);
    }

    @Bean
    public DeleteCommitUseCase deleteCommit(CommitGateway commitGateway){
        return new DeleteCommitUseCaseImp(commitGateway);
    }
}
