package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.infra.DTO.LinksDTO;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import com.example.StarterHub.infra.requests.create.CreateLinksRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LinksMapper {

    public Links toDomain(LinksDTO dto){
        return new Links(
                dto.id(),
                dto.link(),
                dto.userProperties().getId()
        );
    }

    public Links toDomain(CreateLinksRequest request){
        return new Links(
                null,
                request.link(),
                request.userPropertiesID()
        );
    }

    public Links toDomain(String request, UUID userPropertiesId){
        return new Links(
                null,
                request,
                userPropertiesId
        );
    }

    public Links toDomain(CreateLinksRequest request, UUID userPropertiesID){
        return new Links(
                null,
                request.link(),
                userPropertiesID
        );
    }

    public Links toDomain(LinkModel model){
        return new Links(
                model.getId(),
                model.getLink(),
                model.getUserPropertiesModel().getId()
        );
    }

    public LinkModel toEntity(Links links){
        return new LinkModel(
                links.id(),
                links.link(),
                new UserPropertiesModel(
                        links.userPropertiesID()
                )
        );
    }

    public LinkModel toEntity(Links links, UUID userPropertiesID){
        return new LinkModel(
                links.id(),
                links.link(),
                new UserPropertiesModel(
                        userPropertiesID
                )
        );
    }

    public LinksDTO toDTO(Links domain){
        return new LinksDTO(
                domain.id(),
                domain.link(),
                new UserPropertiesModel(
                        domain.userPropertiesID()
                )
        );
    }
}
