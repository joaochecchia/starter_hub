package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.repositories.LinkRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teste")
public class Teste {

    private final LinkRepository linkRepository;

    public Teste(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping("/find/{id}")
    @Transactional
    public ArrayList<LinkModel> findAll(@PathVariable UUID id){
        Optional<List<LinkModel>> a = linkRepository.findAllByUserPropertiesModelId(id);

        return a.get().stream().collect(Collectors.toCollection(ArrayList::new));
    }

}
