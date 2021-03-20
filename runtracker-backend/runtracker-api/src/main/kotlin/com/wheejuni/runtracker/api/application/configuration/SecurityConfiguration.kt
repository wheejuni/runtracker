package com.wheejuni.runtracker.api.application.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.wheejuni.runtracker.api.application.security.converter.JwtHeaderParser
import com.wheejuni.runtracker.api.application.security.handlers.ApiAuthenticationFailureHandler
import com.wheejuni.runtracker.api.application.security.handlers.ApiAuthenticationSuccessHandler
import com.wheejuni.runtracker.api.application.security.managers.IdPasswordAuthenticationManager
import com.wheejuni.runtracker.api.application.security.managers.JwtAuthenticationManager
import com.wheejuni.runtracker.api.application.security.service.RuntrackerUserService
import com.wheejuni.runtracker.domain.application.model.auth.LoginRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.util.pattern.PathPattern
import org.springframework.web.util.pattern.PathPatternParser
import reactor.core.publisher.Mono
import java.io.IOException

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@EnableWebFluxSecurity
@Configuration
class SecurityConfiguration {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var jwtAuthenticationManager: JwtAuthenticationManager

    @Autowired
    private lateinit var loginAuthSuccessHandler: ApiAuthenticationSuccessHandler

    @Autowired
    private lateinit var apiAuthFailHandler: ApiAuthenticationFailureHandler

    @Autowired
    private lateinit var jwtAuthConverter: JwtHeaderParser

    @Autowired
    private lateinit var userService: RuntrackerUserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Bean
    fun securityConfigurationChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .authorizeExchange()
            .anyExchange()
            .permitAll()
            .and()
            .addFilterAt(jwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .addFilterBefore(idPasswordAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build()

    private fun jwtAuthenticationFilter(): AuthenticationWebFilter {
        val filter = AuthenticationWebFilter(jwtAuthenticationManager)
        filter.setAuthenticationFailureHandler(apiAuthFailHandler)
        filter.setServerAuthenticationConverter(jwtAuthConverter)

        return filter
    }

    private fun idPasswordAuthenticationFilter(): AuthenticationWebFilter {
        val filter = AuthenticationWebFilter(IdPasswordAuthenticationManager(userService, passwordEncoder))
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler)
        filter.setAuthenticationFailureHandler(apiAuthFailHandler)
        filter.setServerAuthenticationConverter {exchange ->
            exchange.request.body.next().flatMap { buffer ->
                try {
                    val loginRequest = objectMapper.readValue(buffer.asInputStream(), LoginRequest::class.java)
                    return@flatMap Mono.just(loginRequest)
                } catch (e: IOException) {
                    return@flatMap Mono.error<LoginRequest>(e)
                }
            }.map { UsernamePasswordAuthenticationToken(it.id, it.password)}
        }
        filter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/formlogin"))

        return filter
    }
}