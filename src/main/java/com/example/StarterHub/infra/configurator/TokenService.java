package com.example.StarterHub.infra.configurator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.requests.JWTUserResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenService {

    public String generateToken(UserModel user){
        Algorithm algorithm = Algorithm.HMAC256("palavra-secreta");

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("phone", user.getPhoneNumber())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API-Banco")
                .sign(algorithm);
    }

    public Optional<JWTUserResponse> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("palavra-secreta");

            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);

            UUID userId = UUID.fromString(decode.getClaim("id").asString());

            return Optional.of(new JWTUserResponse(
                    userId,
                    decode.getSubject()
                    ));

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
