package com.wheejuni.runtracker.infra.auth

import com.wheejuni.runtracker.application.auth.UserinfoManagementService
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class SocialAuthenticationManager(
        private val service: UserinfoManagementService): ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {

    }
}