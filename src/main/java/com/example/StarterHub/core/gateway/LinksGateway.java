package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Links;

import java.util.*;

public interface LinksGateway {
    Optional<Links> postLinks(Links links);
    Optional<ArrayList<Links>> postAllLinks(ArrayList<Links> allLinks);
    Optional<ArrayList<Links>> findAllLinksByUserPropertiesId(UUID id);
    Optional<Links> searchLinks(UUID id);
    Optional<Links> editLinks(UUID id, Links links);
    String deleteLinks(UUID id);
    boolean linkExists(String link);
}
