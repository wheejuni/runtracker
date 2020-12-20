package com.wheejuni.runtracker.infra.auth.manager

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

class SocialTokenUserCreationAuthenticationManager: ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}