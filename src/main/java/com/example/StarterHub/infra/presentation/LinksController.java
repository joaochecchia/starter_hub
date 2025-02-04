package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.useCases.Links.*;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import com.example.StarterHub.infra.requests.create.CreateLinksRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starter-hub/users/links")
public class LinksController {
    public final PostLinksUseCase postLinksUseCase;
    public final PostAllLinksUseCase postAllLinksUseCase;
    public final FindAllLinksByUserPropertiesIdUseCase findAllLinksByUserPropertiesIdUseCase;
    public final SearchLinksUseCase searchLinksUseCase;
    public final EditLinksUseCase editLinksUseCase;
    public final DeleteLinksUseCase deleteLinksUseCase;
    public final LinksMapper mapper;

    public LinksController(PostLinksUseCase postLinksUseCase, PostAllLinksUseCase postAllLinksUseCase, FindAllLinksByUserPropertiesIdUseCase findAllLinksByUserPropertiesIdUseCase, SearchLinksUseCase searchLinksUseCase, EditLinksUseCase editLinksUseCase, DeleteLinksUseCase deleteLinksUseCase, LinksMapper mapper) {
        this.postLinksUseCase = postLinksUseCase;
        this.postAllLinksUseCase = postAllLinksUseCase;
        this.findAllLinksByUserPropertiesIdUseCase = findAllLinksByUserPropertiesIdUseCase;
        this.searchLinksUseCase = searchLinksUseCase;
        this.editLinksUseCase = editLinksUseCase;
        this.deleteLinksUseCase = deleteLinksUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> createLink(@Valid @RequestBody CreateLinksRequest request){
        Optional<Links> newLink = postLinksUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Links successfully saved.");
        response.put("Body: ", newLink.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/insertAll")
    public ResponseEntity<Map<String, Object>> createAllLinks(@Valid @RequestBody ArrayList<CreateLinksRequest> allLinks){
        Optional<ArrayList<Links>> newLinks = postAllLinksUseCase.execute(allLinks.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new)));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Links successfully saved.");
        response.put("Body: ", newLinks.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Map<String, Object>> findLink(@PathVariable UUID id){
        Optional<Links> link = searchLinksUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Link successfully found.");
        response.put("Body: ", link.get());

        return ResponseEntity.ok(response);
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<Map<String, Object>> findAllByUserPropertiesID(@PathVariable UUID id){
        Optional<ArrayList<Links>> allLinks = findAllLinksByUserPropertiesIdUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Links successfully found.");
        response.put("Body: ", allLinks.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editLinks(@PathVariable UUID id, @Valid @RequestBody Links links){
        Optional<Links> editedLink = editLinksUseCase.execute(id, links);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Links successfully found.");
        response.put("Body: ", editedLink.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLinks(@PathVariable UUID id){
        return deleteLinksUseCase.execute(id);
    }
}
