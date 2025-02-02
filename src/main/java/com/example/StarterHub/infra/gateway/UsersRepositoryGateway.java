package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;
import com.example.StarterHub.core.validation.LoginRequest;
import com.example.StarterHub.core.validation.LoginResponse;
import com.example.StarterHub.infra.Mapper.UsersMapper;
import com.example.StarterHub.infra.configurator.TokenService;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.persistence.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UsersRepositoryGateway implements UsersGateway {

    private final UserRepository userRepository;
    private final UsersMapper mapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UsersRepositoryGateway(UsersMapper mapper, UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<LoginResponse> loginUsers(LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserModel userModel = (UserModel) authentication.getPrincipal ();
        String token = tokenService.generateToken(userModel);

        return Optional.of(new LoginResponse(token));
    }

    @Override
    public Optional<Users> registerUsers(Users users) {
        String encodedPassword = encoder.encode(users.password());
        UserModel entity = mapper.toEntity(users);
        entity.setPassword(encodedPassword);
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
    @Transactional
    public Map<String, Object> userExist(String username, String email, String phoneNumber) {
        Map<String, Object> check = new HashMap<>();
        System.out.println(username + email + phoneNumber);
        if (userRepository.findByEmail(email).isPresent()) check.put("Email", "Email" + email + " already exists.");
        if (userRepository.findByUsername(username).isPresent()) check.put("Username", "Username " + username + " already exists.");
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) check.put("PhoneNumber", "Phone number " + phoneNumber + " already exists.");

        return check;
    }
}
