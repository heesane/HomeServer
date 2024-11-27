package hhs.server.auth;


import hhs.server.auth.dto.JwtToken;
import hhs.server.auth.service.AuthService;
import hhs.server.domain.model.dto.request.auth.UserLoginRequest;
import hhs.server.domain.model.dto.request.auth.UserRegisterRequest;
import hhs.server.domain.model.dto.response.ResultResponse;
import hhs.server.domain.model.dto.response.auth.UserRegisterResponse;
import hhs.server.domain.model.type.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class UserAuthController {

  private final AuthService JwtAuthService;

  @GetMapping(value = "")
  public ResponseEntity<ResultResponse> oauthLogin(
      @RequestParam("a") String accessToken,
      @RequestParam("r") String refreshToken) {
    JwtToken jwtToken = JwtToken.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
    return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_LOGIN_SUCCESS, jwtToken));
  }

  @PostMapping(value = "/login", consumes = {"multipart/form-data"})
  public ResponseEntity<ResultResponse> login(
      @RequestBody UserLoginRequest jwtLoginRequest) {
    JwtToken login = JwtAuthService.login(jwtLoginRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_LOGIN_SUCCESS, login));
  }

  @PostMapping(value = "/register", consumes = {"multipart/form-data"})
  public ResponseEntity<ResultResponse> register(
      @RequestBody UserRegisterRequest userRegisterRequest) {
    UserRegisterResponse register = JwtAuthService.register(userRegisterRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_CREATED, register));
  }
}
