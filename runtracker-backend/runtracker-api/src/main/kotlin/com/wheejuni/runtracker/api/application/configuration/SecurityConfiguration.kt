package com.wheejuni.runtracker.api.application.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration {

    @Bean
    fun securityConfigurationChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange().anyExchange().permitAll()

        return http.build()
    }
}