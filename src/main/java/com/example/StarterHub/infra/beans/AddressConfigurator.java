package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.AddressGateway;
import com.example.StarterHub.core.useCases.Address.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AddressConfigurator {

    @Bean
    public PostAddressUseCase createAddress(AddressGateway addressGateway){
        return new PostAddressUseCaseImp(addressGateway);
    }

    @Bean
    public SearchAddressUseCase searchAddress(AddressGateway addressGateway){
        return new SearchAddressUseCaseImp(addressGateway);
    }

    @Bean
    public EditAddressUseCase editAddress(AddressGateway addressGateway){
        return new EditAddressUseCaseImp(addressGateway);
    }

    @Bean
    public DeleteAddressUseCase deleteAddress(AddressGateway addressGateway){
        return new DeleteAddressUseCaseImp(addressGateway);
    }
}
