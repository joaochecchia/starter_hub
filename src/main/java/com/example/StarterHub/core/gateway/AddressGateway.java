package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.domain.Links;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface AddressGateway {
    Optional<Address> postAddress(Address newAddress);
    Optional<Address> searchAddress(UUID id);
    Optional<Address> editAddress(UUID id, Address editAddress);
    String deleteAddress(UUID id);
}
