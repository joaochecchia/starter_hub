package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.useCases.UserProperties.*;
import com.example.StarterHub.infra.requests.EditRequest;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import com.example.StarterHub.infra.requests.create.CreateUserPropertiesRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUserProperties(@RequestBody CreateUserPropertiesRequest request){
        Optional<UserProperties> newUserProperties = postUserPropertiesUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Profile details successfully saved!");
        response.put("Body", newUserProperties.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserProperties> searchUserProperties(@PathVariable UUID id){
        Optional<UserProperties> newUserProperties = searchUserPropertiesUseCase.execute(id);

        return ResponseEntity.ok(newUserProperties.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserProperties> editUserProperties(@PathVariable UUID id, @RequestBody EditRequest editRequest){
        Optional<UserProperties> updatedUserProperties = editUserPropertiesUseCase.execute(id, editRequest);

        return ResponseEntity.ok(updatedUserProperties.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserProperties(@PathVariable UUID id){
        String result = deleteUserPropertiesUseCase.execute(id);

        return ResponseEntity.ok(result);
    }
}
