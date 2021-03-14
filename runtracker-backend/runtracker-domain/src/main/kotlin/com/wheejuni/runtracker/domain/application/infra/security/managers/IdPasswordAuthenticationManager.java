package com.wheejuni.runtracker.domain.application.infra.security.managers;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * Created by quo.barlow@kakaocorp.com(정휘준)
 * 2021/03/14
 */
public class IdPasswordAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return null;
    }
}
