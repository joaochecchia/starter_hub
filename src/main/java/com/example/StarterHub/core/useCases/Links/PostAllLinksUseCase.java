package com.example.StarterHub.core.useCases.Links;

import com.example.StarterHub.core.domain.Links;

import java.util.ArrayList;
import java.util.Optional;

public interface PostAllLinksUseCase {
    Optional<ArrayList<Links>> execute(ArrayList<Links> allLinks);
}
