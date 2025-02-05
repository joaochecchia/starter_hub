package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.*;
import com.example.StarterHub.infra.requests.EditRequest;
import com.example.StarterHub.infra.persistence.entities.*;
import com.example.StarterHub.infra.requests.create.CreateUserPropertiesRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserPropertiesMapper {

    private final LinksMapper linksMapper;
    private final AddressMapper addressMapper;
    private final RepositoryMapper repositoryMapper;

    public UserPropertiesMapper(LinksMapper linksMapper, AddressMapper addressMapper, RepositoryMapper repositoryMapper) {
        this.linksMapper = linksMapper;
        this.addressMapper = addressMapper;
        this.repositoryMapper = repositoryMapper;
    }


    public UserProperties toDomain(CreateUserPropertiesRequest request){

        byte[] photo = null;
        if(request.encodedPhoto() != null){
            try{
                photo = Base64.getDecoder().decode(request.encodedPhoto());
            } catch (IllegalArgumentException e){
            }
        }

        return new UserProperties(
                null,
                request.description(),
                photo,
                request.company(),
                new ArrayList<>(),
                null,
                new ArrayList<>(),
                new Users(
                        request.usersId(),
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    public UserProperties toDomain(CreateUserPropertiesRequest request, UUID userId){

        byte[] photo = null;
        if(request.encodedPhoto() != null){
            try{
                photo = Base64.getDecoder().decode(request.encodedPhoto());
            } catch (IllegalArgumentException e){
            }
        }

        return new UserProperties(
                null,
                request.description(),
                photo,
                request.company(),
                new ArrayList<>(),
                null,
                new ArrayList<>(),
                new Users(
                        userId,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    public UserProperties toDomain(UserPropertiesModel model, UUID userId){
        if(model == null) return null;

        Users user = new Users(
                userId,
                model.getUserModel().getUsername(),
                model.getUserModel().getPassword(),
                model.getUserModel().getEmail(),
                model.getUserModel().getPhoneNumber()
        );

        ArrayList<Links> linksDomain = model.getLinkModel() == null ? new ArrayList<>() :
                model.getLinkModel().stream()
                        .map(linksMapper::toDomain)
                        .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Repository> repositories = model.getRepositoryModel() == null ? new ArrayList<>() :
                model.getRepositoryModel().stream()
                        .map(repositoryMapper::toDomain)
                        .collect(Collectors.toCollection(ArrayList::new));

        Address addressDomain = model.getAddressModel() == null ? null :
                addressMapper.toDomain(model.getAddressModel());

        return new UserProperties(
                model.getId(),
                model.getDescription(),
                model.getPhoto(),
                model.getCompany(),
                linksDomain,
                addressDomain,
                repositories,
                user
        );
    }

    public UserPropertiesModel toEntity(UserProperties domain){
        if(domain == null) return null;

        UserModel userModel = new UserModel();
        userModel.setId(domain.users().id());
        userModel.setEmail(domain.users().email());
        userModel.setPassword(domain.users().password());
        userModel.setUsername(domain.users().username());
        userModel.setPhoneNumber(domain.users().phone());
        userModel.setUserPropertiesModel(null);

        ArrayList<LinkModel> linksModel = domain.links().stream()
                .map(linksMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<RepositoryModel> repositories = domain.repositories().stream()
                .map(repositoryMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        AddressModel addressModel = domain.address() == null ? null :
                addressMapper.toEntity(domain.address());

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(domain.id());
        model.setDescription(domain.description());
        model.setPhoto(domain.photo());
        model.setCompany(domain.company());
        model.setUserModel(userModel);
        model.setRepositoryModel(repositories);
        model.setLinkModel(linksModel);
        model.setAddressModel(addressModel);

        return model;
    }

    public UserPropertiesModel toEntity(UserProperties domain, UUID userID){
        if(domain == null) return null;

        UserModel userModel = new UserModel();
        userModel.setId(userID);
        userModel.setEmail(domain.users().email());
        userModel.setPassword(domain.users().password());
        userModel.setUsername(domain.users().username());
        userModel.setPhoneNumber(domain.users().phone());
        userModel.setUserPropertiesModel(null);

        ArrayList<LinkModel> linksModel = domain.links().stream()
                .map(linksMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<RepositoryModel> repositories = domain.repositories().stream()
                .map(repositoryMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        AddressModel addressModel = domain.address() == null ? null :
                addressMapper.toEntity(domain.address());

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(domain.id());
        model.setDescription(domain.description());
        model.setPhoto(domain.photo());
        model.setCompany(domain.company());
        model.setUserModel(userModel);
        model.setRepositoryModel(repositories);
        model.setLinkModel(linksModel);
        model.setAddressModel(addressModel);

        return model;
    }


    public UserPropertiesModel toModel(EditRequest request){
        if(request == null) return null;

        byte[] photo = null;
        if(request.encodedPhoto() != null){
            photo = Base64.getDecoder().decode(request.encodedPhoto());
        }

        UserModel userModel = new UserModel();
        userModel.setId(request.user().id());
        userModel.setUsername(request.user().username());
        userModel.setPhoneNumber(request.user().phone());
        userModel.setEmail(request.user().email());
        userModel.setPassword(request.user().password());
        userModel.setUserPropertiesModel(null);

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(request.id());
        model.setDescription(request.description());
        model.setPhoto(photo);
        model.setCompany(request.company());
        model.setUserModel(userModel);
        model.setRepositoryModel(null);
        model.setLinkModel(null);

        return model;
    }
}
