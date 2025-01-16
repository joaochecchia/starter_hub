package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.useCases.Address.DeleteAddressUseCase;
import com.example.StarterHub.core.useCases.Address.EditAddressUseCase;
import com.example.StarterHub.core.useCases.Address.PostAddressUseCase;
import com.example.StarterHub.core.useCases.Address.SearchAddressUseCase;
import com.example.StarterHub.infra.DTO.AddressDTO;
import com.example.StarterHub.infra.Mapper.AddressMapper;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/address")
public class AddressController {
    private final PostAddressUseCase postAddressUseCase;
    private final SearchAddressUseCase searchAddressUseCase;
    private final EditAddressUseCase editAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final AddressMapper mapper;


    public AddressController(PostAddressUseCase postAddressUseCase, SearchAddressUseCase searchAddressUseCase, EditAddressUseCase editAddressUseCase, DeleteAddressUseCase deleteAddressUseCase, AddressMapper mapper) {
        this.postAddressUseCase = postAddressUseCase;
        this.searchAddressUseCase = searchAddressUseCase;
        this.editAddressUseCase = editAddressUseCase;
        this.deleteAddressUseCase = deleteAddressUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> insertAddress(@RequestBody AddressDTO request){
        Optional<Address> address = postAddressUseCase.execute(mapper.toDomain(request));

        return ResponseEntity.ok(address.get());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable UUID id){
        Optional<Address> address = searchAddressUseCase.execute(id);

        return ResponseEntity.ok(address.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Address> editAddress(@PathVariable UUID id, @RequestBody  AddressDTO request){
        Optional<Address> edit = editAddressUseCase.execute(id, mapper.toDomain(request));

        return ResponseEntity.ok(edit.get());
    }

    @DeleteMapping("/insert/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable UUID id){
        String deleteStatment = deleteAddressUseCase.execute(id);

        return ResponseEntity.ok(deleteStatment);
    }
}
