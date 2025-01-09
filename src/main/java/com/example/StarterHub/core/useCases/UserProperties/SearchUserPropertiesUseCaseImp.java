package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.Optional;

public class SearchUserPropertiesUseCaseImp implements SearchUserPropertiesUseCase {
    @Override
    public Optional<UserProperties> execute(String name) {
        return Optional.empty();
    }
}
