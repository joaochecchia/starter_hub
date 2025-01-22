package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.infra.DTO.LinksDTO;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import com.example.StarterHub.infra.requests.create.CreateLinksRequest;
import org.springframework.stereotype.Component;

@Component
public class LinksMapper {

    public Links toDomain(LinksDTO dto){
        return new Links(
                dto.id(),
                dto.link(),
                dto.userProperties().getId()
        );
    }

    public Links toDomain(CreateLinksRequest reqeust){
        return new Links(
                null,
                reqeust.link(),
                reqeust.userPropertiesID()
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
