package com.wheejuni.runtracker.infra

import com.wheejuni.runtracker.application.auth.UserinfoManagementService
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfiguration(
        private val userinfoManagementService: UserinfoManagementService) {


}