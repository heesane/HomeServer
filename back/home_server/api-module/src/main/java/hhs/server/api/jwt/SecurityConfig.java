package hhs.server.api.jwt;

import hhs.server.api.jwt.filter.JwtTokenFilter;
import hhs.server.api.jwt.filter.LoginAuthenticationFilter;
import hhs.server.api.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Key;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final Key secretKey;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class).build();

        http.authenticationManager(authenticationManager);

        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/**").permitAll()
                )
//                .addFilterAt(this.abstractAuthenticationProcessingFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(this.JwtTokenFilter(),UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        return new LoginAuthenticationFilter("/api/login", authenticationManager);
    }

    private JwtTokenFilter JwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenUtil,secretKey,userService);
    }
}
