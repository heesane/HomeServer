package hhs.server.api.jwt.filter;

import hhs.server.api.jwt.JwtTokenUtil;
import hhs.server.api.service.UserService;
import hhs.server.domain.persistence.User;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Key;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final Key secretKey;
    private final UserService userService;

    // 들어오는 모든 요청에 대한 Filter 적용JwtFilterChain
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Bearer ~~~
        String accessTokenString = validateToken(request);
        if(accessTokenString == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 사용자의 Access Token
        Claims claims = jwtTokenUtil.extractClaims(accessTokenString);
        Long userId = jwtTokenUtil.extractUserIdFromClaims(accessTokenString);
        User loginUser = userService.getUser(userId);

        // Expired Check ( Access Token이 만료되기 전)
        if(!jwtTokenUtil.isExpired(claims)) {
            // 바로 통과
            Authentication authentication = jwtTokenUtil.getAuthentication(accessTokenString);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        // Expired Check ( Access Token 만료된 후, Refresh Token 통한 Token 재발급)
        else{
            // users 테이블에서 해당 유저의 refresh token 조회
            String userRefreshToken = loginUser.getRefreshToken();

            // Refresh Token 없는 경우
            if(userRefreshToken == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Refresh Token이 있는 경우
            Claims refreshTokenClaims = jwtTokenUtil.extractClaims(userRefreshToken);

            // Refresh Token이 만료되지 않은 경우
            // 새로운 Access Token 발급
            if(!jwtTokenUtil.isExpired(refreshTokenClaims)) {
                String newAccessToken = jwtTokenUtil.generateToken(userId).accessToken();
                response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken);

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                filterChain.doFilter(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
            return token.split(" ")[1];
        }
        return null;
    }
}
