package hhs.server.auth.service;


import hhs.server.auth.dto.JwtToken;
import hhs.server.domain.model.dto.request.auth.UserLoginRequest;
import hhs.server.domain.model.dto.request.auth.UserRegisterRequest;
import hhs.server.domain.model.dto.response.auth.UserRegisterResponse;
import hhs.server.domain.persistence.User;

public interface AuthService {

  UserRegisterResponse register(UserRegisterRequest userRegisterDto);

  JwtToken login(UserLoginRequest userLoginDto);

  User getUser(Long userId);
}
