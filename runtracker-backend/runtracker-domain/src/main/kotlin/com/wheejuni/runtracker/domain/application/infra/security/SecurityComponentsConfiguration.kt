package com.wheejuni.runtracker.domain.application.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.wheejuni.runtracker.domain.application.presentation.JSONSerializeConfiguration
import com.wheejuni.runtracker.domain.application.service.RuntrackerUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@Configuration
@Import(JSONSerializeConfiguration::class)
class SecurityComponentsConfiguration {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var userService: RuntrackerUserService

    @Bean
    fun jwtAuthenticationFilter(): AuthenticationWebFilter {
        val filter = AuthenticationWebFilter()


    }
}