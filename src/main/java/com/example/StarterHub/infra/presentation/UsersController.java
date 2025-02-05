package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.useCases.Address.PostAddressUseCase;
import com.example.StarterHub.core.useCases.Links.PostAllLinksUseCase;
import com.example.StarterHub.core.useCases.User.*;

import com.example.StarterHub.core.useCases.UserProperties.PostUserPropertiesUseCase;
import com.example.StarterHub.core.validation.LoginResponse;
import com.example.StarterHub.infra.Mapper.AddressMapper;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import com.example.StarterHub.infra.Mapper.UsersMapper;
import com.example.StarterHub.core.validation.LoginRequest;
import com.example.StarterHub.infra.requests.RegisterUserRequest;
import com.example.StarterHub.infra.requests.create.CreateAddressRequest;
import com.example.StarterHub.infra.requests.create.CreateUserPropertiesRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starter-hub/user")
@CrossOrigin(origins = "*")
public class UsersController {

    private final RegisterUsersUseCase registerUsersUseCase;
    private final LoginUsersUserCase loginUsersUserCase;
    private final PostAddressUseCase postAddressUseCase;
    private final PostUserPropertiesUseCase postUserPropertiesUseCase;
    private final PostAllLinksUseCase postAllLinksUseCase;
    private final UsersMapper mapper;
    private final UserPropertiesMapper userPropertiesMapper;
    private final AddressMapper addressMapper;
    private final LinksMapper linksMapper;

    public UsersController(RegisterUsersUseCase registerUsersUseCase, LoginUsersUserCase loginUsersUserCase, PostAddressUseCase postAddressUseCase, PostUserPropertiesUseCase postUserPropertiesUseCase, PostAllLinksUseCase postAllLinksUseCase, UsersMapper mapper, UserPropertiesMapper userPropertiesMapper, AddressMapper addressMapper, LinksMapper linksMapper) {
        this.registerUsersUseCase = registerUsersUseCase;
        this.loginUsersUserCase = loginUsersUserCase;
        this.postAddressUseCase = postAddressUseCase;
        this.postUserPropertiesUseCase = postUserPropertiesUseCase;
        this.postAllLinksUseCase = postAllLinksUseCase;
        this.mapper = mapper;
        this.userPropertiesMapper = userPropertiesMapper;
        this.addressMapper = addressMapper;
        this.linksMapper = linksMapper;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUsers(@Valid @RequestBody RegisterUserRequest request){
        ArrayList<String> linksRequest = request.createLinksRequest();
        Optional<Users> newUser = registerUsersUseCase.execute(mapper.toDomain(request.userRequest()));

        List<Object> responseArray = new ArrayList<>();
        responseArray.add(newUser.get());

        CreateUserPropertiesRequest userPropertiesRequest = new CreateUserPropertiesRequest(request.description(), null, request.company(), newUser.get().id());
        Optional<UserProperties> newUserProperties = postUserPropertiesUseCase.execute(userPropertiesMapper.toDomain(userPropertiesRequest));
        responseArray.add(newUserProperties.get());

        if(request.location() != null && request.country() != null && request.postalCode() != null){
            System.out.println("Estive no if do address");
            CreateAddressRequest addressRequest = new CreateAddressRequest(request.country(), request.postalCode(), request.location(), newUserProperties.get().id());
            Optional<Address> newAddress = postAddressUseCase.execute(addressMapper.toDomain(addressRequest));
            responseArray.add(newAddress.get());
        }

        if(request.createLinksRequest() != null){
            System.out.println("Estive no if do Link");
            Optional<ArrayList<Links>> newLinks = postAllLinksUseCase.execute(linksRequest.stream()
                    .map(item -> linksMapper.toDomain(item, newUserProperties.get()
                    .id()))
                    .collect(Collectors.toCollection(ArrayList::new)));

            responseArray.add(newLinks.get());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "User successfully signed up.");
        response.put("Body: ", responseArray);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUsers(@Valid @RequestBody LoginRequest request){
        Optional<LoginResponse> login = loginUsersUserCase.execute(request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "User successfully signed!");
        response.put("Body", login.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}