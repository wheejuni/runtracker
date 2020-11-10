package com.wheejuni.runtracker.infra.auth.converter

import com.wheejuni.runtracker.infra.auth.token.InApplicationAuthenticationToken
import org.springframework.data.mongodb.core.index.Index
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

const val AUTHORIZATION_HEADER_KEY = "Authorization"
const val BEARER_TOKEN_PREFIX = "Bearer "

@Component
class BearerTokenAuthenticationConverter: ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        if(exchange == null) {
            throw BadCredentialsException("invalid web exchange request")
        }

        val header: MutableList<String> = exchange.request.headers[AUTHORIZATION_HEADER_KEY]
                ?: throw BadCredentialsException("invalid header format")

        val tokenValue = try {
            header[0].split(BEARER_TOKEN_PREFIX)[1]
        } catch (error: IndexOutOfBoundsException) {
            throw BadCredentialsException("invalid bearer token format")
        }

        return Mono.just(InApplicationAuthenticationToken(tokenValue))
    }
}