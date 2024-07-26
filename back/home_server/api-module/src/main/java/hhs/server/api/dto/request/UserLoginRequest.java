package hhs.server.api.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class UserLoginRequest {

    private String username;

    private String password;
}
