package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.useCases.User.*;
import com.example.StarterHub.infra.DTO.UsersDTO;

import com.example.StarterHub.infra.Mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/starter-hub/user")
public class UsersController {

    private final RegisterUsersUseCase registerUsersUseCase;
    private final LoginUsersUserCase loginUsersUserCase;
    private final EditUsersUseCase editUsersUseCase;
    private final SearchUsersUseCase searchUsersUseCase;
    private final DeleteUsersUseCase deleteUsersUseCase;
    private final UsersMapper mapper;

    public UsersController(RegisterUsersUseCase registerUsersUseCase, LoginUsersUserCase loginUsersUserCase, EditUsersUseCase editUsersUseCase, SearchUsersUseCase searchUsersUseCase, DeleteUsersUseCase deleteUsersUseCase, UsersMapper mapper) {
        this.registerUsersUseCase = registerUsersUseCase;
        this.loginUsersUserCase = loginUsersUserCase;
        this.editUsersUseCase = editUsersUseCase;
        this.searchUsersUseCase = searchUsersUseCase;
        this.deleteUsersUseCase = deleteUsersUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUsers(@RequestBody UsersDTO dto){
        Optional<Users> newUser = registerUsersUseCase.execute(mapper.toDomain(dto));

        return ResponseEntity.ok(mapper.toDTO(newUser.get()));
    }
}
