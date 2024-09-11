package hhs.server.domain.model.dto.request.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserLoginRequest {


  private final String email;

  private final String password;
}
