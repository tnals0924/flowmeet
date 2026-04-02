package kr.flowmeet.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    String secretKey,
    String issuer,
    long accessTokenExpiry
) {
}