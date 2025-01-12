package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.infra.Mapper.UserPropertiesMapper;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import com.example.StarterHub.infra.persistence.repositories.UserPropertiesRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPropertiesRepositoryGateway implements UserPropertiesGateway {

    private final UserPropertiesRepository userPropertiesRepository;
    private final UserPropertiesMapper mapper;

    public UserPropertiesRepositoryGateway(UserPropertiesRepository userPropertiesRepository, UserPropertiesMapper mapper) {
        this.userPropertiesRepository = userPropertiesRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserProperties> postUserProperties(UserProperties userProperties) {
        UserPropertiesModel model = mapper.fromDomain(userProperties);
        UserPropertiesModel newUserProperties = userPropertiesRepository.save(model);

        return Optional.of(mapper.toDomain(newUserProperties));
    }

    @Override
    public Optional<UserProperties> searchUserProperties(UUID id) {
        Optional<UserPropertiesModel> user = userPropertiesRepository.findById(id);

        return Optional.of(mapper.toDomain(user.get()));
    }

    @Override
    public Optional<UserProperties> editUserProperties(UUID id, UserProperties userProperties) {
        Optional<UserPropertiesModel> find = userPropertiesRepository.findById(id);

        if(find.isPresent()){
            UserPropertiesModel model = mapper.fromDomain(userProperties);
            model.setId(id);
            UserPropertiesModel edit = userPropertiesRepository.save(model);

            return Optional.of(mapper.toDomain(edit));
        }

        return Optional.empty();
    }

    @Override
    public String deleteUserProperties(UUID id) {
        userPropertiesRepository.deleteById(id);

        return "Usuário deletado com sucesso";
    }
}
