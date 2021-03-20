package com.wheejuni.runtracker.api.application.security.managers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.wheejuni.runtracker.api.application.security.tokens.RuntrackerUserToken;
import com.wheejuni.runtracker.api.application.security.tokens.preauthorize.JwtAuthenticationToken;
import com.wheejuni.runtracker.api.application.security.service.RuntrackerUserService;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Bomeehouse studios (me@wheejuni.com)
 * 2021/03/19
 */
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private RuntrackerUserService userService;

    public JwtAuthenticationManager(RuntrackerUserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
            .map(auth -> (JwtAuthenticationToken) auth)
            .filter(JwtAuthenticationToken::isAuthenticationEligible)
            .map(token -> JWT.decode(token.getCredentials()))
            .map(jwt -> jwt.getClaim("userid").as(Long.class))
            .flatMap(id -> {
                Mono<RuntrackerUserToken> result = this.userService.getUserInfoByUserId(id);
                return Objects.requireNonNullElseGet(result, Mono::empty);
            }).switchIfEmpty(Mono.defer(() -> Mono.just(new RuntrackerUserToken(null, null))))
            .cast(Authentication.class);
    }
}
