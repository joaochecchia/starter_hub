package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.infra.exceptions.NotFoundObjectByIdentifierException;
import com.example.StarterHub.infra.persistence.repositories.UserRepository;
import com.example.StarterHub.infra.requests.EditRequest;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import com.example.StarterHub.infra.Mapper.UsersMapper;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import com.example.StarterHub.infra.persistence.repositories.UserPropertiesRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPropertiesRepositoryGateway implements UserPropertiesGateway {

    private final UserPropertiesRepository userPropertiesRepository;
    private final UserRepository userRepository;
    private final UserPropertiesMapper mapper;
    private final UsersMapper usersMapper;

    public UserPropertiesRepositoryGateway(UserPropertiesRepository userPropertiesRepository, UserRepository userRepository, UserPropertiesMapper userPropertiesMapper, UsersMapper usersMapper) {
        this.userPropertiesRepository = userPropertiesRepository;
        this.userRepository = userRepository;
        this.mapper = userPropertiesMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    public Optional<UserProperties> postUserProperties(UserProperties userProperties) {
        if(userRepository.findById(userProperties.users().id()).isEmpty()){
            throw new NotFoundObjectByIdentifierException("Don't have any user with " + userProperties.users().id() + " ID.");
        }
        UserPropertiesModel newUserProperties = userPropertiesRepository.save(mapper.toEntity(userProperties));

        return Optional.of(mapper.toDomain(newUserProperties, newUserProperties.getUserModel().getId()));
    }

    @Override
    public Optional<UserProperties> searchUserProperties(UUID id) {
        Optional<UserPropertiesModel> user = userPropertiesRepository.findById(id);
        if(user.isEmpty()) throw new NotFoundObjectByIdentifierException("Object with " + id + "Not found");

        return Optional.of(mapper.toDomain(user.get(), user.get().getUserModel().getId()));
    }

    @Override
    public Optional<UserProperties> editUserProperties(UUID id, EditRequest request) {
        Optional<UserPropertiesModel> find = userPropertiesRepository.findById(id);

        if(find.isPresent()){
            UserPropertiesModel model = mapper.toModel(request);
            model.setId(id);
            UserPropertiesModel edit = userPropertiesRepository.save(model);

            return Optional.of(mapper.toDomain(edit, edit.getUserModel().getId()));
        }

        throw new NotFoundObjectByIdentifierException("Object with " + id + "Not found");
    }

    @Override
    public String deleteUserProperties(UUID id) {
        Optional<UserPropertiesModel> userProperties = userPropertiesRepository.findById(id);

        if(userProperties.isPresent()){
            userPropertiesRepository.deleteById(id);

            return "Usuário deletado com sucesso";
        }

        throw new NotFoundObjectByIdentifierException("Object with " + id + "Not found");
    }
}
