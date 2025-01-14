package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.UUID;

public class DeleteLinksUseCaseImp implements DeleteLinksUseCase{

    private final LinksGateway linksGateway;

    public DeleteLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public String execute(UUID id) {
        return linksGateway.deleteLinks(id);
    }
}
