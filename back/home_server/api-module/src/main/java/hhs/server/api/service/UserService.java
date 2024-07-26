package hhs.server.api.service;


import hhs.server.api.dto.request.UserLoginRequest;
import hhs.server.api.dto.request.UserRegisterRequest;
import hhs.server.domain.model.JwtToken;
import hhs.server.domain.persistence.User;

public interface UserService {

    User register(UserRegisterRequest userRegisterDto);

    JwtToken login(UserLoginRequest userLoginDto);

    User getUser(Long userId);
}
