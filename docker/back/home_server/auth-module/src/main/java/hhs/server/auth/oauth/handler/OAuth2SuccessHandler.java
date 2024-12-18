package hhs.server.auth.oauth.handler;


import hhs.server.auth.service.TokenComponent;
import hhs.server.domain.exception.ExceptionCode;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.domain.model.type.UserRole;
import hhs.server.domain.persistence.User;
import hhs.server.domain.repository.jpa.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final TokenComponent tokenComponent;
  private final UserRepository userRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
      , Authentication authentication) throws IOException {

    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    String email = oAuth2User.getAttribute("email");

    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException(ExceptionCode.USER_NOT_FOUND)
    );
    Long userId;

    if (user != null) { // 기존 회원인 경우 액세스, 리프레시 토큰 생성 후 전달
      userId = user.getId();

    } else { // 신규 회원인 경우 회원가입 페이지로 이동
      User newOAuth2User = User.builder()
          .email(email)
          .nickname(oAuth2User.getAttribute("name"))
          .role(UserRole.USER)
          .provider("google")
          .providerId(oAuth2User.getAttribute("sub"))
          .build();
      User savedOAuthUser = userRepository.save(newOAuth2User);
      userId = savedOAuthUser.getId();
    }

    String targetUrl = UriComponentsBuilder.fromUriString(
            "http://localhost:8080/api/v1/auth")
        .queryParam("a", tokenComponent.generateAccessToken(userId))
        .queryParam("r", tokenComponent.generateRefreshToken())
        .build()
        .toUriString();
    getRedirectStrategy().sendRedirect(request, response, targetUrl);

  }
}
