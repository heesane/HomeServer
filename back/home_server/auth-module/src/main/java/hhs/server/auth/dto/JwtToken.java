package hhs.server.auth.dto;

import lombok.Builder;

@Builder
public record JwtToken(
    String accessToken,
    String refreshToken
) {

}