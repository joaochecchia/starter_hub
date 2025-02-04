package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.useCases.Address.DeleteAddressUseCase;
import com.example.StarterHub.core.useCases.Address.EditAddressUseCase;
import com.example.StarterHub.core.useCases.Address.PostAddressUseCase;
import com.example.StarterHub.core.useCases.Address.SearchAddressUseCase;
import com.example.StarterHub.infra.DTO.AddressDTO;
import com.example.StarterHub.infra.Mapper.AddressMapper;
import com.example.StarterHub.infra.requests.create.CreateAddressRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> insertAddress(@Valid  @RequestBody CreateAddressRequest request){
        Optional<Address> newAddress = postAddressUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Address successfully saved!");
        response.put("Body: ", newAddress.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> findAddress(@PathVariable UUID id){
        Optional<Address> address = searchAddressUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Address successfully found!");
        response.put("Body: ", address.get());


        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editAddress(@PathVariable UUID id, @Valid @RequestBody  AddressDTO request){
        Optional<Address> edit = editAddressUseCase.execute(id, mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Address successfully edited!");
        response.put("Body :", edit.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/insert/{id}")
    public ResponseEntity<Map<String, Object>> deleteAddress(@PathVariable UUID id){
        String deleteStatment = deleteAddressUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Address successfully deleted!");
        response.put("Body :", deleteStatment);

        return ResponseEntity.ok(response);
    }
}
