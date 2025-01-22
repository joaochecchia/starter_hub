package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;
import com.example.StarterHub.infra.exeptions.NotFoundObjectByIdentifierException;

import java.util.Optional;
import java.util.UUID;

public class EditLinksUseCaseImp implements EditLinksUseCase{

    private final LinksGateway linksGateway;

    public EditLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<Links> execute(UUID id, Links links) {
        if (linksGateway.linkExists(links.link())){
            throw new NotFoundObjectByIdentifierException("The Link " + links.link() + " already exist.");
        }

        return linksGateway.editLinks(id, links);
    }
}
