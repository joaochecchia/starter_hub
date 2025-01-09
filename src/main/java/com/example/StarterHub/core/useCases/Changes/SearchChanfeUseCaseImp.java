package com.example.StarterHub.core.useCases.Changes;


import com.example.StarterHub.core.domain.Changes;

import java.util.Optional;
import java.util.UUID;

public class SearchChanfeUseCaseImp implements SearchChangeUseCase {
    @Override
    public Optional<Changes> execute(UUID changesId) {
        return Optional.empty();
    }
}
