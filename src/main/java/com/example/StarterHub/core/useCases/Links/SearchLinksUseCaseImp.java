package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchLinksUseCaseImp implements SearchLinksUseCase{
    private final LinksGateway linksGateway;

    public SearchLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<Links> execute(UUID id) {
        return linksGateway.searchLinks(id);
    }
}
