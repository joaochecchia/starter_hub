package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import com.example.StarterHub.infra.exceptions.NotFoundObjectByIdentifierException;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.repositories.LinkRepository;
import com.example.StarterHub.infra.persistence.repositories.UserPropertiesRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LinksRepositoryGateway implements LinksGateway {

    private final LinkRepository linkRepository;
    private final UserPropertiesRepository userPropertiesRepository;
    private final LinksMapper mapper;

    public LinksRepositoryGateway(LinkRepository linkRepository, UserPropertiesRepository userPropertiesRepository, LinksMapper mapper) {
        this.linkRepository = linkRepository;
        this.userPropertiesRepository = userPropertiesRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Links> postLinks(Links links) {
        if(userPropertiesRepository.findById(links.userPropertiesID()).isEmpty()){
            throw new NotFoundObjectByIdentifierException("Don't have any user specs " + links.userPropertiesID() + " with this id.");
        }
        LinkModel link = linkRepository.save(mapper.toEntity(links));

        return Optional.of(mapper.toDomain(link));
    }

    @Override
    public Optional<ArrayList<Links>> postAllLinks(ArrayList<Links> allLinks) {
        if(userPropertiesRepository.findById(allLinks.get(0).userPropertiesID()).isEmpty()) {
            throw new NotFoundObjectByIdentifierException("Don't have any user specs " + allLinks.get(0).userPropertiesID() + " with this id.");
        }

        List<LinkModel> newLinks = linkRepository.saveAll(allLinks.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new)));

        return Optional.of(newLinks.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public Optional<ArrayList<Links>> findAllLinksByUserPropertiesId(UUID id) {
        Optional<ArrayList<LinkModel>> links = linkRepository.findAllByUserPropertiesModelId(id);
        if(links.get().isEmpty()) throw new NotFoundObjectByIdentifierException("Don't have any link for this  user");

        return Optional.of(links.get().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public Optional<Links> searchLinks(UUID id) {
        Optional<LinkModel> link = linkRepository.findById(id);
        if(link.isEmpty()) throw new NotFoundObjectByIdentifierException("Link with " + id + "doesn't exist.");

        return Optional.of(mapper.toDomain(link.get()));
    }

    @Override
    public Optional<Links> editLinks(UUID id, Links links) {
        Optional<LinkModel> find = linkRepository.findById(id);

        if(find.isPresent()){
            LinkModel model = mapper.toEntity(links);
            model.setId(id);

            LinkModel updatedLink = linkRepository.save(model);
            return Optional.of(mapper.toDomain(updatedLink));
        }

        throw new NotFoundObjectByIdentifierException("Link with " + links.link() + " doesn't exist.");
    }

    @Override
    public String deleteLinks(UUID id) {
        Optional<LinkModel> found = linkRepository.findById(id);

        if (found.isPresent()) {
            linkRepository.deleteById(id);

            return "Usuário deletado com sucesso.";
        }

        throw new NotFoundObjectByIdentifierException("Link with " + id + " doesn't exist.");
    }

    @Override
    public boolean linkExists(String link) {
        Optional<LinkModel> userExist = linkRepository.findByLink(link);

        return userExist.isPresent();
    }
}
