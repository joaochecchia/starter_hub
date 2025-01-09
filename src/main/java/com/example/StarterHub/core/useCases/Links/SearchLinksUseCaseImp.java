package com.example.StarterHub.core.useCases.Links;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class SearchLinksUseCaseImp implements SearchLinksUseCase{
    @Override
    public Optional<ArrayList<String>> execute(UUID userId) {
        return Optional.empty();
    }
}
