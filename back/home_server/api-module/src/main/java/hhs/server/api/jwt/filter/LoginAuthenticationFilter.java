package hhs.server.api.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhs.server.api.dto.request.UserLoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
                                        final AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String method = request.getMethod();

        if(!method.equals("POST")){
            throw new UnsupportedOperationException("This Method is not supported : "+method);
        }

        ServletInputStream inputStream = request.getInputStream();

        UserLoginRequest userLoginRequest = new ObjectMapper().readValue(inputStream, UserLoginRequest.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        ));
    }
}
