package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.LinksGateway;
import com.example.StarterHub.core.useCases.Links.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinksConfigrator {

    @Bean
    public PostLinksUseCase insertLink(LinksGateway linksGateway){
        return new PostLinksUseCaseImp(linksGateway);
    }

    @Bean
    public PostAllLinksUseCase insertAllLinks(LinksGateway linksGateway){
        return new PostAllLinksUseCaseImp(linksGateway);
    }

    @Bean
    public SearchLinksUseCase searchLinks(LinksGateway linksGateway){
        return new SearchLinksUseCaseImp(linksGateway);
    }

    @Bean
    public FindAllLinksByUserPropertiesIdUseCase findAllLinks(LinksGateway linksGateway){
        return new FindAllLinksByUserPropertiesIdUseCaseImp(linksGateway);
    }

    @Bean
    public EditLinksUseCase editLink(LinksGateway linksGateway){
        return new EditLinksUseCaseImp(linksGateway);
    }

    @Bean
    public DeleteLinksUseCase deleteLink(LinksGateway linksGateway){
        return new DeleteLinksUseCaseImp(linksGateway);
    }
}
