package com.gabriel.ferreira.ms_customer.infra.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabriel.ferreira.ms_customer.domain.enums.ErrorCodes;
import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import com.gabriel.ferreira.ms_customer.infra.exception.constant.ErrorConstant;
import com.gabriel.ferreira.ms_customer.infra.exception.token.TokenCriarException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ms-customer")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new TokenCriarException(
                    new ExceptionResponse(ErrorCodes.JWT_TOKEN_CRIAR_ERROR, ErrorConstant.JWT_TOKEN_CRIAR_ERROR)
            );
        }
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var email = JWT.require(algorithm)
                    .withIssuer("ms-customer")
                    .build()
                    .verify(token)
                    .getSubject();
            return email;
        }catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
