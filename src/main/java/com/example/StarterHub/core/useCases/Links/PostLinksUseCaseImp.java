package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.Optional;
import java.util.UUID;

public class PostLinksUseCaseImp implements PostLinksUseCase {

    private final LinksGateway linksGateway;

    public PostLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<Links> execute(Links links) {
        return linksGateway.postLinks(links);
    }
}
