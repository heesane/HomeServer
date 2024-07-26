package hhs.server.api.controller;

import hhs.server.api.dto.request.UserLoginRequest;
import hhs.server.api.dto.request.UserRegisterRequest;
import hhs.server.api.service.UserService;
import hhs.server.domain.model.JwtToken;
import hhs.server.domain.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserLoginRequest userLoginDto){

        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        return ResponseEntity.ok(userService.register(userRegisterRequest));
    }

}
