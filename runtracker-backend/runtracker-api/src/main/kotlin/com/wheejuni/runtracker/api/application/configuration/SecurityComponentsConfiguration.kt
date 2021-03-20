package com.wheejuni.runtracker.api.application.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.wheejuni.runtracker.api.application.security.converter.JwtHeaderParser
import com.wheejuni.runtracker.api.application.security.handlers.ApiAuthenticationFailureHandler
import com.wheejuni.runtracker.api.application.security.handlers.ApiAuthenticationSuccessHandler
import com.wheejuni.runtracker.api.application.security.managers.IdPasswordAuthenticationManager
import com.wheejuni.runtracker.api.application.security.managers.JwtAuthenticationManager
import com.wheejuni.runtracker.api.application.security.service.RuntrackerUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
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
    fun bcryptEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}