package com.wheejuni.runtracker.infra.auth.converter

import com.wheejuni.runtracker.infra.auth.token.InApplicationAuthenticationToken
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

const val AUTHORIZATION_HEADER_KEY = "Authorization"

@Component
class BearerTokenAuthenticationConverter: ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        if(exchange == null) {
            throw BadCredentialsException("invalid web exchange request")
        }

        val header: MutableList<String> = exchange.request.headers[AUTHORIZATION_HEADER_KEY]
                ?: throw BadCredentialsException("invalid header format")

        return Mono.just(InApplicationAuthenticationToken(header[0]))
    }
}