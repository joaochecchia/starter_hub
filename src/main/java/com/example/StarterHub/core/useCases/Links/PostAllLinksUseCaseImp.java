package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.ArrayList;
import java.util.Optional;

public class PostAllLinksUseCaseImp implements PostAllLinksUseCase{

    private final LinksGateway linksGateway;

    public PostAllLinksUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<ArrayList<Links>> execute(ArrayList<Links> allLinks) {
        return linksGateway.postAllLinks(allLinks);
    }
}
