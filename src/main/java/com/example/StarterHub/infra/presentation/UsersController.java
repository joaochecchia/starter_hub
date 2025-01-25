package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.useCases.User.*;

import com.example.StarterHub.core.validation.LoginResponse;
import com.example.StarterHub.infra.Mapper.UsersMapper;
import com.example.StarterHub.core.validation.LoginRequest;
import com.example.StarterHub.infra.configurator.TokenService;
import com.example.StarterHub.infra.requests.create.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/starter-hub/user")
public class UsersController {

    private final RegisterUsersUseCase registerUsersUseCase;
    private final LoginUsersUserCase loginUsersUserCase;
    private final EditUsersUseCase editUsersUseCase;
    private final SearchUsersUseCase searchUsersUseCase;
    private final DeleteUsersUseCase deleteUsersUseCase;
    private final TokenService tokenService;
    private final UsersMapper mapper;

    public UsersController(RegisterUsersUseCase registerUsersUseCase, LoginUsersUserCase loginUsersUserCase, EditUsersUseCase editUsersUseCase, SearchUsersUseCase searchUsersUseCase, DeleteUsersUseCase deleteUsersUseCase, TokenService tokenService, UsersMapper mapper) {
        this.registerUsersUseCase = registerUsersUseCase;
        this.loginUsersUserCase = loginUsersUserCase;
        this.editUsersUseCase = editUsersUseCase;
        this.searchUsersUseCase = searchUsersUseCase;
        this.deleteUsersUseCase = deleteUsersUseCase;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUsers(@Valid @RequestBody CreateUserRequest request){
        Optional<Users> newUser = registerUsersUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "User successfully signed!");
        response.put("Body", newUser.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

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
