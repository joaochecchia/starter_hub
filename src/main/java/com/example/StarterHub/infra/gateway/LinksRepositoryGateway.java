package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.gateway.LinksGateway;
import com.example.StarterHub.infra.Mapper.LinksMapper;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.repositories.LinkRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LinksRepositoryGateway implements LinksGateway {

    private final LinkRepository linkRepository;
    private final LinksMapper mapper;

    public LinksRepositoryGateway(LinkRepository linkRepository, LinksMapper mapper) {
        this.linkRepository = linkRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Links> postLinks(Links links) {
        LinkModel a  = mapper.toEntity(links);
        LinkModel link = linkRepository.save(a);

        return Optional.of(mapper.toDomain(link));
    }

    @Override
    public Optional<List<Links>> findAllLinksByUserPropertiesId(UUID id) {
        return linkRepository.findAllByUserPropertiesModelId(id)
                .map(linkModels ->
                        linkModels.stream()
                                .map(mapper::toDomain)
                                .collect(Collectors.toList())
                );
    }


    @Override
    public Optional<Links> searchLinks(UUID id) {
        Optional<LinkModel> link = linkRepository.findById(id);

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

        return Optional.empty();
    }

    @Override
    public String deleteLinks(UUID id) {
        Optional<LinkModel> found = linkRepository.findById(id);

        if (found.isPresent()) {
            linkRepository.deleteById(id);

            return "Usuário deletado com sucesso.";
        }

        return "Usuário não existe no banco de dados";
    }
}
