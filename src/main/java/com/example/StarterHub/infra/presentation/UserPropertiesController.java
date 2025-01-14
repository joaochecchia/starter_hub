package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.useCases.UserProperties.*;
import com.example.StarterHub.core.validation.EditRequest;
import com.example.StarterHub.infra.DTO.UserPropertiesDTO;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users")
public class UserPropertiesController {

    private final PostUserPropertiesUseCase postUserPropertiesUseCase;
    private final SearchUserPropertiesUseCase searchUserPropertiesUseCase;
    private final EditUserPropertiesUseCase editUserPropertiesUseCase;
    private final DeleteUserPropertiesUseCase deleteUserPropertiesUseCase;
    private final UserPropertiesMapper mapper;

    public UserPropertiesController(PostUserPropertiesUseCase postUserPropertiesUseCase, SearchUserPropertiesUseCase searchUserPropertiesUseCase, EditUserPropertiesUseCase editUserPropertiesUseCase, DeleteUserPropertiesUseCase deleteUserPropertiesUseCase, UserPropertiesMapper mapper) {
        this.postUserPropertiesUseCase = postUserPropertiesUseCase;
        this.searchUserPropertiesUseCase = searchUserPropertiesUseCase;
        this.editUserPropertiesUseCase = editUserPropertiesUseCase;
        this.deleteUserPropertiesUseCase = deleteUserPropertiesUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<UserPropertiesDTO> createUserProperties(@RequestBody UserPropertiesDTO request){
        Optional<UserProperties> newUserProperties = postUserPropertiesUseCase.execute(mapper.toDomain(request, request.userModel().getId()));

        return ResponseEntity.ok(mapper.toDTO(newUserProperties.get()));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserPropertiesDTO> searchUserProperties(@PathVariable UUID id){
        Optional<UserProperties> newUserProperties = searchUserPropertiesUseCase.execute(id);

        return ResponseEntity.ok(mapper.toDTO(newUserProperties.get()));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserPropertiesDTO> editUserProperties(@PathVariable UUID id, @RequestBody EditRequest editRequest){
        Optional<UserProperties> updatedUserProperties = editUserPropertiesUseCase.execute(id, editRequest);

        return ResponseEntity.ok(mapper.toDTO(updatedUserProperties.get()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserProperties(@PathVariable UUID id){
        deleteUserPropertiesUseCase.execute(id);

        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }
}
