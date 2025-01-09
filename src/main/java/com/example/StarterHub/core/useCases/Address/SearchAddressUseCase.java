package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;

import java.util.Optional;
import java.util.UUID;

public interface SearchAddressUseCase {
    Optional<Address> execute(UUID id);
}
