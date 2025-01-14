package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.Optional;
import java.util.UUID;

public class EditLinksUseCaseImp implements EditLinksUseCase{

    private final LinksGateway linksGateway;

    public EditLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<Links> execute(UUID id, Links links) {
        return linksGateway.editLinks(id, links);
    }
}
