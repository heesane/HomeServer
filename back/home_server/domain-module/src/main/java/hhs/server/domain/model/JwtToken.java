package hhs.server.domain.model;

import lombok.Builder;

@Builder
public record JwtToken (
        String accessToken,
        String refreshToken
)
{

}
