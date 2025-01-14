package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.validation.EditRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LinksGateway {
    Optional<Links> postLinks(Links links);
    Optional<ArrayList<Links>> findAllLinksByUserPropertiesId(UUID id);
    Optional<Links> searchLinks(UUID id);
    Optional<Links> editLinks(UUID id, Links links);
    String deleteLinks(UUID id);
}
