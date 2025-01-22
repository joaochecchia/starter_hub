package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;
import com.example.StarterHub.infra.Mapper.UsersMapper;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UsersRepositoryGateway implements UsersGateway {

    private final UserRepository userRepository;
    private final UsersMapper mapper;

    public UsersRepositoryGateway(UsersMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> loginUsers(Users users) {
        return Optional.empty();
    }

    @Override
    public Optional<Users> registerUsers(Users users) {
        UserModel entity = mapper.toEntity(users);
        UserModel newUser = userRepository.save(entity);

        return Optional.of(mapper.toDomain(newUser));
    }

    @Override
    public Optional<Users> searchUsers(UUID id) {
        Optional<UserModel> opt = userRepository.findById(id);
        Users user = mapper.toDomain(opt.get());

        return Optional.of(user);
    }

    @Override
    public Optional<Users> editUsers(UUID id, Users users) {
        Optional<UserModel> findUser= userRepository.findById(id);

        if(findUser.isPresent()){
            UserModel model = mapper.toEntity(users);
            model.setId(id);
            UserModel update = userRepository.save(model);

            return Optional.of(mapper.toDomain(update));
        }

        return Optional.empty();
    }

    @Override
    public String deleteUsers(UUID id) {
        Optional<UserModel> find = userRepository.findById(id);

        if(find.isPresent()){
            userRepository.deleteById(id);

            return "Usuário deletado com sucesso";
        }

        return "Usuário não encontrado";
    }

    @Override
    public Map<String, Object> userExist(String username, String email, String phoneNumber) {
        Map<String, Object> check = new HashMap<>();
        System.out.println(username + email + phoneNumber);
        if (userRepository.findByEmail(email).isPresent()) check.put("Email", "Email" + email + " already exists.");
        if (userRepository.findByUsername(username).isPresent()) check.put("Username", "Username " + username + " already exists.");
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) check.put("PhoneNumber", "Phone number " + phoneNumber + " already exists.");

        return check;
    }
}
