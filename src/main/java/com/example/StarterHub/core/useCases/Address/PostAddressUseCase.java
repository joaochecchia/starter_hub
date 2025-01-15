package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;

import java.util.Optional;

public interface PostAddressUseCase {
    Optional<Address> execute(Address newAddress);
}
