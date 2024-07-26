package hhs.server.api.service.impl;


import hhs.server.api.dto.request.UserLoginRequest;
import hhs.server.api.dto.request.UserRegisterRequest;
import hhs.server.api.jwt.JwtTokenUtil;
import hhs.server.api.service.UserService;
import hhs.server.domain.model.JwtToken;
import hhs.server.domain.persistence.User;
import hhs.server.domain.repository.UserRepository;
import hhs.server.domain.type.UserRole;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public User register(UserRegisterRequest userRegisterDto) {

        if(userRepository.existsByEmail(userRegisterDto.getEmail())){
            throw new RuntimeException("User with email already exists");
        }
        if(userRepository.existsByName(userRegisterDto.getUsername())){
            throw new RuntimeException("User with name already exists");
        }

        String hashedPassword = encoder.encode(userRegisterDto.getPassword());

        return userRepository.save(
                User.builder()
                        .name(userRegisterDto.getUsername())
                        .email(userRegisterDto.getEmail())
                        .password(hashedPassword)
                        .role(UserRole.ROLE_USER)
                        .build()
        );
    }

    @Override
    public JwtToken login(UserLoginRequest userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        User user = userRepository.findByName(username).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        if(!encoder.matches(userLoginDto.getPassword(),user.getPassword())){
            return null;
        }

        return jwtTokenUtil.generateToken(user.getId());
    }


    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}
