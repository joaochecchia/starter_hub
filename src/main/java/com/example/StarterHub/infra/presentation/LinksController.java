package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.useCases.Links.*;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import com.example.StarterHub.infra.requests.create.CreateLinksRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/links")
public class LinksController {
    public final PostLinksUseCase postLinksUseCase;
    public final FindAllLinksByUserPropertiesIdUseCase findAllLinksByUserPropertiesIdUseCase;
    public final SearchLinksUseCase searchLinksUseCase;
    public final EditLinksUseCase editLinksUseCase;
    public final DeleteLinksUseCase deleteLinksUseCase;
    public final LinksMapper mapper;

    public LinksController(PostLinksUseCase postLinksUseCase, FindAllLinksByUserPropertiesIdUseCase findAllLinksByUserPropertiesIdUseCase, SearchLinksUseCase searchLinksUseCase, EditLinksUseCase editLinksUseCase, DeleteLinksUseCase deleteLinksUseCase, LinksMapper mapper) {
        this.postLinksUseCase = postLinksUseCase;
        this.findAllLinksByUserPropertiesIdUseCase = findAllLinksByUserPropertiesIdUseCase;
        this.searchLinksUseCase = searchLinksUseCase;
        this.editLinksUseCase = editLinksUseCase;
        this.deleteLinksUseCase = deleteLinksUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<Links> createLink(@RequestBody CreateLinksRequest request){
        Optional<Links> newLink = postLinksUseCase.execute(mapper.toDomain(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newLink.get());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Links> findLink(@PathVariable UUID id){
        Optional<Links> link = searchLinksUseCase.execute(id);

        return ResponseEntity.ok(link.get());
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<ArrayList<Links>> findAllByUserPropertiesID(@PathVariable UUID id){
        Optional<ArrayList<Links>> allLinks = findAllLinksByUserPropertiesIdUseCase.execute(id);

        return ResponseEntity.ok(allLinks.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Links> editLinks(@PathVariable UUID id, @RequestBody Links links){
        Optional<Links> editedLink = editLinksUseCase.execute(id, links);

        return ResponseEntity.ok(editedLink.get());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLinks(@PathVariable UUID id){
        String result = deleteLinksUseCase.execute(id);

        return result;
    }
}
