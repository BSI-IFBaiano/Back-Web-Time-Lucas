package com.web.desenvolvimento.edusphere.infra.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.web.desenvolvimento.edusphere.domain.user.User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;


import java.time.Instant;

@Service
public class TokenService {

    private String secret = "aobaobkaoskdoakodeoakdokaed";
    public String generateToken(User user) {
        try {
            var now = Instant.now();
            var expiresIn = 300L;
            return JWT.create()
                    .withIssuer("backend")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getIdUser().toString())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(now.plusSeconds(expiresIn))
                    .sign(Algorithm.HMAC256(secret));

        }catch (JWTCreationException exception){
            throw new RuntimeException("errorrrr");
        }
    }

    public DecodedJWT validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("backend")
                    .build();
            return verifier.verify(token);
        }catch (JWTVerificationException exception) {
            return null;
        }
    }
}
