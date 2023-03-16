package com.beandon.auth.services;

import com.beandon.auth.interfaces.RefreshRepository;
import com.beandon.auth.pojos.users.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final long EXPIRE_TIME_S = 3600L; // 1 Hour

    private final JwtEncoder encoder;

    private final RefreshRepository repo;

    public String getToken(String subject, String scope) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRE_TIME_S))
                .subject(subject)
                .claim("scope", scope)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getToken(Authentication authentication) {
        return getToken(authentication.getName(), getScopeFromAuth(authentication));
    }

    private String getScopeFromAuth(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    public String getRefresh(Authentication authentication) {
        Auth auth = Auth.builder()
                .id(UUID.randomUUID().toString())
                .subject(authentication.getName())
                .scope(getScopeFromAuth(authentication))
                .build();
        repo.save(auth);
        return auth.getId();
    }

    public String refreshToken(String refresh, String name) {
        Optional<Auth> maybeAuth = repo.findById(refresh);
        if (maybeAuth.isPresent() && maybeAuth.get().getSubject().equals(name)) {
            return getToken(maybeAuth.get().getSubject(), maybeAuth.get().getScope());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stored refresh token with that ID");
    }
}
