package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;
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
    private final UserPropertiesMapper mapper;
    private final UsersMapper usersMapper;

    public UserPropertiesRepositoryGateway(UserPropertiesRepository userPropertiesRepository, UserPropertiesMapper userPropertiesMapper, UsersMapper usersMapper) {
        this.userPropertiesRepository = userPropertiesRepository;
        this.mapper = userPropertiesMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    public Optional<UserProperties> postUserProperties(UserProperties userProperties) {
        System.out.println("ENTITY: " + mapper.toEntity(userProperties).toString());
        UserPropertiesModel newUserProperties = userPropertiesRepository.save(mapper.toEntity(userProperties));

        return Optional.of(mapper.toDomain(newUserProperties, newUserProperties.getUserModel().getId()));
    }

    @Override
    public Optional<UserProperties> searchUserProperties(UUID id) {
        Optional<UserPropertiesModel> user = userPropertiesRepository.findById(id);

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

        return Optional.empty();
    }

    @Override
    public String deleteUserProperties(UUID id) {
        Optional<UserPropertiesModel> userProperties = userPropertiesRepository.findById(id);

        if(userProperties.isPresent()){
            userPropertiesRepository.deleteById(id);

            return "Usuário deletado com sucesso";
        }

        return "Usuário não encontrado";
    }
}
