package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.gateway.AddressGateway;

import java.util.UUID;

public class DeleteAddressUseCaseImp implements DeleteAddressUseCase{

    private final AddressGateway addressGateway;

    public DeleteAddressUseCaseImp(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    @Override
    public String execute(UUID id) {
        return addressGateway.deleteAddress(id);
    }
}
