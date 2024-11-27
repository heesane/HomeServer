package hhs.server.domain.model.dto.request.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserRegisterRequest {

  private final String email;

  private final String password;

  private final String nickname;
}
