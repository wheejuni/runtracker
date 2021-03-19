package com.wheejuni.runtracker.domain.application.infra.security.converter

import com.auth0.jwt.JWT
import com.wheejuni.runtracker.domain.application.infra.security.tokens.preauthorize.JwtAuthenticationToken
import com.wheejuni.runtracker.domain.exception.ServerSecurityException
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */

const val AUTHENTICATION_HEADER_PREFIX = "Bearer "
const val JWT_USERID_CLAIM = "userid"

@Component
class JwtHeaderParser: ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        val safeExchange = exchange ?: throw IllegalArgumentException("NO REQUEST PROVIDED")
        val authHeader = safeExchange.request.headers["Authorization"]
            ?: throw ServerSecurityException("Authorization header가 없음")

        return Mono.just(JwtAuthenticationToken(authHeader.first()))
    }
}
