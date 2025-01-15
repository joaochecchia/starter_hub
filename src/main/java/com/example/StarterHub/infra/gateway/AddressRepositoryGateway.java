package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.gateway.AddressGateway;
import com.example.StarterHub.infra.Mapper.AddressMapper;
import com.example.StarterHub.infra.persistence.entities.AddressModel;
import com.example.StarterHub.infra.persistence.repositories.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AddressRepositoryGateway implements AddressGateway {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;

    public AddressRepositoryGateway(AddressRepository addressRepository, AddressMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Address> postAddress(Address address) {
        System.out.println("ESSA È A ENTITY: " + mapper.toEntity(address).toString());
        AddressModel newAddress = addressRepository.save(mapper.toEntity(address));

        return Optional.of(mapper.toDomain(newAddress));
    }

    @Override
    public Optional<Address> searchAddress(UUID id) {
        Optional<AddressModel> search = addressRepository.findById(id);

        return Optional.of(mapper.toDomain(search.get()));
    }

    @Override
    public Optional<Address> editAddress(UUID id, Address editAddress) {
        Optional<AddressModel> find = addressRepository.findById(id);

        if(find.isPresent()){
            AddressModel temp = mapper.toEntity(editAddress);
            temp.setId(id);
            AddressModel editedAddress = addressRepository.save(temp);

            return Optional.of(mapper.toDomain(editedAddress));
        }

        return Optional.empty();
    }

    @Override
    public String deleteAddress(UUID id) {
        Optional<AddressModel> find = addressRepository.findById(id);

        if(find.isPresent()){
            addressRepository.deleteById(id);

            return "Address successful deleted!";
        }

        return "Address not found.";
    }
}
