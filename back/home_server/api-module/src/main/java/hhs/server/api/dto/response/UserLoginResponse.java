package hhs.server.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class UserLoginResponse {

    private String accessToken;

    private String refreshToken;
}
