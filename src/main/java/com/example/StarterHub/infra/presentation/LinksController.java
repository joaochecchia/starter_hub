package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.useCases.Links.*;
import com.example.StarterHub.core.useCases.User.SearchUsersUseCase;
import com.example.StarterHub.infra.DTO.LinksDTO;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/Links")
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

    @PostMapping("/register")
    public ResponseEntity<LinksDTO> createLink(@RequestBody LinksDTO request){
        System.out.println("COMO CHEGA O REQUEST" + request.toString());
        Links a = mapper.toDomain(request);
        System.out.println("DEPOIS DA CONVERSÂO PARA DOMAIN" + a.toString());
        Optional<Links> newLink = postLinksUseCase.execute(a);

        return ResponseEntity.ok(mapper.toDTO(newLink.get()));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Links> findLink(@PathVariable UUID id){
        Optional<Links> link = searchLinksUseCase.execute(id);

        return ResponseEntity.ok(link.get());
    }

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
