package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.useCases.UserProperties.*;
import com.example.StarterHub.infra.requests.EditRequest;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import com.example.StarterHub.infra.requests.create.CreateUserPropertiesRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/starter-hub/users")
public class UserPropertiesController {

    private final PostUserPropertiesUseCase postUserPropertiesUseCase;
    private final SearchUserPropertiesUseCase searchUserPropertiesUseCase;
    private final SearchUserPropertiesByUserIdUseCase searchUserPropertiesByUserIdUseCase;
    private final EditUserPropertiesUseCase editUserPropertiesUseCase;
    private final DeleteUserPropertiesUseCase deleteUserPropertiesUseCase;
    private final UserPropertiesMapper mapper;

    public UserPropertiesController(PostUserPropertiesUseCase postUserPropertiesUseCase, SearchUserPropertiesUseCase searchUserPropertiesUseCase, SearchUserPropertiesByUserIdUseCase searchUserPropertiesByUserIdUseCase, EditUserPropertiesUseCase editUserPropertiesUseCase, DeleteUserPropertiesUseCase deleteUserPropertiesUseCase, UserPropertiesMapper mapper) {
        this.postUserPropertiesUseCase = postUserPropertiesUseCase;
        this.searchUserPropertiesUseCase = searchUserPropertiesUseCase;
        this.searchUserPropertiesByUserIdUseCase = searchUserPropertiesByUserIdUseCase;
        this.editUserPropertiesUseCase = editUserPropertiesUseCase;
        this.deleteUserPropertiesUseCase = deleteUserPropertiesUseCase;
        this.mapper = mapper;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUserProperties(@Valid @RequestBody CreateUserPropertiesRequest request){
        Optional<UserProperties> newUserProperties = postUserPropertiesUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Profile details successfully saved!");
        response.put("Body: ", newUserProperties.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Map<String, Object>> searchUserProperties(@PathVariable UUID id){
        Optional<UserProperties> UserProperties = searchUserPropertiesUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "User specs found.");
        response.put("Body: ", UserProperties.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchByUser/{id}")
    public ResponseEntity<Map<String, Object>> searchUserPropertiesBuUserId(@PathVariable UUID id){
        Optional<UserProperties> userProperties = searchUserPropertiesByUserIdUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "User specs found.");
        response.put("Body: ", userProperties.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editUserProperties(@PathVariable UUID id, @Valid @RequestBody EditRequest editRequest){
        Optional<UserProperties> updatedUserProperties = editUserPropertiesUseCase.execute(id, editRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "User specs successfully saved.");
        response.put("Body: ", updatedUserProperties);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserProperties(@PathVariable UUID id){
        String result = deleteUserPropertiesUseCase.execute(id);

        return ResponseEntity.ok(result);
    }
}
