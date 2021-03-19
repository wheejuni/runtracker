package com.wheejuni.runtracker.domain.application

import com.fasterxml.jackson.databind.ObjectMapper
import com.wheejuni.runtracker.domain.application.infra.security.converter.JwtHeaderParser
import com.wheejuni.runtracker.domain.application.infra.security.handlers.ApiAuthenticationFailureHandler
import com.wheejuni.runtracker.domain.application.infra.security.handlers.ApiAuthenticationSuccessHandler
import com.wheejuni.runtracker.domain.application.infra.security.managers.JwtAuthenticationManager
import com.wheejuni.runtracker.domain.application.service.RuntrackerUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@ComponentScan(basePackages = ["com.wheejuni.runtracker.domain.application"])
@Configuration
class SecurityComponentsConfiguration {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var apiAuthenticationConverter: JwtHeaderParser

    @Autowired
    private lateinit var userService: RuntrackerUserService

    @Bean
    fun jwtAuthenticationManager(): JwtAuthenticationManager = JwtAuthenticationManager(userService)

    @Bean
    fun successHandler(): ApiAuthenticationSuccessHandler = ApiAuthenticationSuccessHandler(objectMapper)

    @Bean
    fun failureHandler(): ApiAuthenticationFailureHandler = ApiAuthenticationFailureHandler()

    @Bean
    fun jwtAuthenticationFilter(): AuthenticationWebFilter {
        val filter = AuthenticationWebFilter(jwtAuthenticationManager())
        filter.setAuthenticationSuccessHandler(successHandler())
        filter.setAuthenticationFailureHandler(failureHandler())
        filter.setServerAuthenticationConverter(this.apiAuthenticationConverter)

        return filter
    }
}