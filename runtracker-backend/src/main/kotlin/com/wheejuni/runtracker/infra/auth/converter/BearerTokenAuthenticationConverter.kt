package com.wheejuni.runtracker.infra.auth.converter

import com.wheejuni.runtracker.infra.auth.token.InApplicationAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

const val AUTHORIZATION_HEADER_KEY = "Authorization"

@Component
class BearerTokenAuthenticationConverter: ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return exchange?.let {
            val header = it.request.headers[AUTHORIZATION_HEADER_KEY]?.get(0)
            return Mono.just(InApplicationAuthenticationToken(header))

        } ?: Mono.just(InApplicationAuthenticationToken(""))
    }
}