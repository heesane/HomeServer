package hhs.server.api.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
public class JwtSecretKey {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public SecretKey customSecretKey() {
        String encodedSecret = Base64.getEncoder().encodeToString(secret.getBytes());
        return Keys.hmacShaKeyFor(encodedSecret.getBytes());
    }
}
