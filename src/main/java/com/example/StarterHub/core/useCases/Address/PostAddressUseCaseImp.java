package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.gateway.AddressGateway;

import java.util.Optional;

public class PostAddressUseCaseImp implements PostAddressUseCase{

    private final AddressGateway addressGateway;

    public PostAddressUseCaseImp(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    @Override
    public Optional<Address> execute(Address newAddress) {
        return addressGateway.postAddress(newAddress);
    }
}
