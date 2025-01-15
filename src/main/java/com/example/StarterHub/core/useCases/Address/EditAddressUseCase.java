package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;

import java.util.Optional;
import java.util.UUID;

public interface EditAddressUseCase {
    Optional<Address> execute(UUID id, Address editAddress);
}
