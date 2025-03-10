package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.gateway.AddressGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchAddressUseCaseImp implements  SearchAddressUseCase{

    private final AddressGateway addressGateway;

    public SearchAddressUseCaseImp(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    @Override
    public Optional<Address> execute(UUID id) {
        return addressGateway.searchAddress(id);
    }
}
