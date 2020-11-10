package com.wheejuni.runtracker.infra.auth.manager

import com.wheejuni.runtracker.infra.auth.token.InApplicationAuthenticationToken
import com.wheejuni.runtracker.infra.auth.token.UserinfoJwtTool
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class BearerJwtTokenAuthenticationManager(
        private val jwtTool: UserinfoJwtTool): ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        return authentication?.let {
            if(it.isAuthenticated) {
                return Mono.just(it)
            }

            if((authentication is InApplicationAuthenticationToken).not()) {
                throw IllegalArgumentException("unsupported auth token type")
            }

            val inAppToken = it as InApplicationAuthenticationToken
            return jwtTool.fromJwt(inAppToken.credentials)
        } ?: kotlin.run {
            throw IllegalArgumentException("auth info must not be null")
        }
    }
}