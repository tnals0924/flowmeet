package kr.flowmeet.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Optional;
import kr.flowmeet.auth.exception.AuthErrorCode;
import kr.flowmeet.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    private SecretKey createSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(final Long userId, final String email, final String name) {
        Date now = new Date();
        return makeToken(
                new Date(now.getTime() + jwtProperties.getAccessTokenExpiry()),
                String.valueOf(userId),
                Map.of(
                        "email", email,
                        "name", name
                )
        );
    }

    private String makeToken(final Date expiry, final String subject, final Map<String, ?> claims) {
        Date now = new Date();

        var builder = Jwts.builder()
                .header().type("JWT").and()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now)
                .expiration(expiry)
                .subject(subject);

        claims.forEach(builder::claim);

        return builder
                .signWith(createSecretKey())
                .compact();
    }

    public boolean validToken(final String token) {
        try {
            if (token == null) return false;

            Jwts.parser()
                    .verifyWith(createSecretKey())
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthException(AuthErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (Exception e) {
            log.error("[validToken] Error: ", e);
            throw new AuthException(AuthErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    public Optional<Long> extractUserIdFromToken(final String token) {
        if (token == null) return Optional.empty();

        try {
            return Optional.of(Long.parseLong(getClaims(token).getSubject()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Claims getClaims(final String token) {
        return Jwts.parser()
                .verifyWith(createSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}