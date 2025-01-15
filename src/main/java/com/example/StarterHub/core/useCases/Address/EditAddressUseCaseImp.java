package com.example.StarterHub.core.useCases.Address;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.gateway.AddressGateway;

import java.util.Optional;
import java.util.UUID;

public class EditAddressUseCaseImp implements EditAddressUseCase{

    private final AddressGateway addressGateway;

    public EditAddressUseCaseImp(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }


    @Override
    public Optional<Address> execute(UUID id, Address editAddress) {
        return addressGateway.editAddress(id, editAddress);
    }
}
