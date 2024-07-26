package hhs.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

    private String username;

    private String password;

    private String email;

    public void hashPassword(){

    }
}
