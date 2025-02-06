package com.back.cookbook.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.back.cookbook.domain.entity.UserEntity;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;  // parâmetro a ser adicionado no algoritmo de hash

    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token =  JWT.create()
                .withIssuer("auth-api") // nome que identifica a aplicação
                .withSubject(user.getLogin())  // usuário que recebe o token
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
            return token;
        }catch(JWTCreationException e){
            throw new RuntimeException("Error while generating token: ", e);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
        }catch(JWTVerificationException e){
            e.printStackTrace();
            throw new RuntimeException("Invalid JWT Token");
        }
    }
}
