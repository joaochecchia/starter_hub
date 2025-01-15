package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.infra.DTO.AddressDTO;
import com.example.StarterHub.infra.persistence.entities.AddressModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toDomain(AddressDTO dto){
        return new Address(
                dto.id(),
                dto.country(),
                dto.postalCode(),
                dto.location(),
                dto.userPropertiesModel().getId()
        );
    }

    public Address toDomain(AddressModel model){
        return new Address(
                model.getId(),
                model.getCountry(),
                model.getPostalCode(),
                model.getLocation(),
                model.getUserPropertiesModel().getId()
        );
    }

    public AddressModel toEntity(Address domain){
        return new AddressModel(
                domain.id(),
                domain.country(),
                domain.postalCode(),
                domain.location(),
                new UserPropertiesModel(
                        domain.userPropertiesId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    public AddressDTO toDTO(Address domain){
        return new AddressDTO(
                domain.id(),
                domain.country(),
                domain.postalCode(),
                domain.location(),
                new UserPropertiesModel(
                        domain.userPropertiesId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

}
