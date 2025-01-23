package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;
import com.example.StarterHub.infra.exceptions.CredentialsAreadyExistsExceptions;

import java.util.Optional;

public class PostLinksUseCaseImp implements PostLinksUseCase {

    private final LinksGateway linksGateway;

    public PostLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<Links> execute(Links links) {

        boolean result = linksGateway.linkExists(links.link());
        if (result){
            throw new CredentialsAreadyExistsExceptions("The link " + links.link() + "already exist.");
        }

        return linksGateway.postLinks(links);
    }
}
