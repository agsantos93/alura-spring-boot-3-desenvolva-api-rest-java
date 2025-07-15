package med.voll.api.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.jpa.Usuario;

@Service
public class TokenService {

    @Value("${security.jwt-token-secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getLogin())
                // .withClaim("id", usuario.getId())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
        return token;
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("API Voll.med").build().verify(tokenJWT).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
