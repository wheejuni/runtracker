package com.wheejuni.runtracker.api.application.security.managers;

import com.wheejuni.runtracker.api.application.security.service.RuntrackerUserService;
import com.wheejuni.runtracker.api.application.security.tokens.RuntrackerUserToken;
import com.wheejuni.runtracker.domain.entity.RuntrackerUser;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

/**
 * Bomeehouse studios (me@wheejuni.com)
 * 2021/03/14
 */
public class IdPasswordAuthenticationManager implements ReactiveAuthenticationManager {

    private RuntrackerUserService userService;
    private PasswordEncoder passwordEncoder;

    public IdPasswordAuthenticationManager(RuntrackerUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
            .cast(UsernamePasswordAuthenticationToken.class)
            .flatMap(token -> this.userService.getUserInfoForFormLogin(token))
            .filter(usertoken -> this.passwordMatches(usertoken.getPrincipal(), (String) authentication.getCredentials()))
            .switchIfEmpty(Mono.defer(() -> Mono.just(RuntrackerUserToken.unauthorizedToken())))
            .cast(Authentication.class);
    }

    private boolean passwordMatches(RuntrackerUser user, String loginRequestedPassword) {
        return this.passwordEncoder.matches(loginRequestedPassword, user.getEncryptedPassword());
    }
}
