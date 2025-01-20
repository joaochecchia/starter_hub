package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindAllLinksByUserPropertiesIdUseCaseImp implements FindAllLinksByUserPropertiesIdUseCase{

    private final LinksGateway linksGateway;

    public FindAllLinksByUserPropertiesIdUseCaseImp(LinksGateway linksGateway) {
        this.linksGateway = linksGateway;
    }

    @Override
    public Optional<ArrayList<Links>> execute(UUID id) {
        return linksGateway.findAllLinksByUserPropertiesId(id);
    }

}
