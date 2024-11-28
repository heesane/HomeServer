package hhs.server.auth.service;

import hhs.server.auth.dto.JwtToken;
import hhs.server.domain.exception.ExceptionCode;
import hhs.server.common.exceptions.exception.DuplicatedEmailException;
import hhs.server.common.exceptions.exception.DuplicatedNicknameException;
import hhs.server.common.exceptions.exception.UnmatchedPasswordException;
import hhs.server.common.exceptions.exception.NotFoundException;
import hhs.server.domain.model.dto.request.auth.UserLoginRequest;
import hhs.server.domain.model.dto.request.auth.UserRegisterRequest;
import hhs.server.domain.model.dto.response.auth.UserRegisterResponse;
import hhs.server.domain.model.type.UserRole;
import hhs.server.domain.persistence.User;
import hhs.server.domain.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthService implements AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;
  private final TokenComponent tokenComponent;

  @Transactional
  @Override
  public UserRegisterResponse register(UserRegisterRequest userRegisterDto) {

    if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
      throw new DuplicatedEmailException();
    }

    if (userRepository.existsByNickname(userRegisterDto.getNickname())) {
      throw new DuplicatedNicknameException();
    }

    String hashedPassword = encoder.encode(userRegisterDto.getPassword());
    userRepository.save(
        User.builder()
            .nickname(userRegisterDto.getNickname())
            .email(userRegisterDto.getEmail())
            .password(hashedPassword)
            .role(UserRole.USER)
            .build()
    );
    return new UserRegisterResponse(userRegisterDto.getEmail(), userRegisterDto.getNickname());
  }

  @Override
  public JwtToken login(UserLoginRequest userLoginDto) {
    String email = userLoginDto.getEmail();
    String password = userLoginDto.getPassword();

    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new NotFoundException(ExceptionCode.USER_NOT_FOUND)
    );

    if (!encoder.matches(password, user.getPassword())) {
      throw new UnmatchedPasswordException();
    }

    return tokenComponent.generateToken(user.getId());
  }

  @Override
  public User getUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(
        () -> new NotFoundException(ExceptionCode.USER_NOT_FOUND)
    );
  }
}
