package hhs.server.api.jwt;

import hhs.server.domain.model.JwtToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenExpiration;

    private Key key;

    @PostConstruct
    protected void init() {
        byte[] encodedSecret = Decoders.BASE64.decode(this.secret);
        this.key = Keys.hmacShaKeyFor(encodedSecret);
    }

    // JWT Token 생성 (AccessToken, RefreshToken)
    public JwtToken generateToken(Long userId){

        long now = (new Date()).getTime();

        Date accessTokenExpiration = new Date(now + this.accessTokenExpiration);
        Date refreshTokenExpired = new Date(now + this.refreshTokenExpiration);

        Claims claims = Jwts.claims();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(accessTokenExpiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpired)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT AccessToken 만료 여부 확인
    // 만료되었으면 true, 아니면 false
    public boolean isExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    // JWT 토큰에서 클레임 추출
    public Claims extractClaims(String accessToken){
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(accessToken).getBody();
    }

    // JWT 토큰에서 사용자 ID 추출
    public Long extractUserIdFromClaims(String accessToken){
        return Long.parseLong(extractClaims(accessToken).getSubject());
    }

    // JWT 토큰에서 사용자 권한 추출
    public Authentication getAuthentication(String accessToken) {

        // Jwt 토큰 복호화
        Claims claims = validateToken(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    // 토큰 검증
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return null;
    }
}
