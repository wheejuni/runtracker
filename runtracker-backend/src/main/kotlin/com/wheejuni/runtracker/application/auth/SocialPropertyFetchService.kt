package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.ApplicationLoginRequest
import com.wheejuni.runtracker.domain.SocialUserProperty
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SocialPropertyFetchService(
        private val connectors: List<SocialProviderConnector<out SocialUserProperty>>) {

    fun retrieveSocialProperties(request: ApplicationLoginRequest): Mono<out SocialUserProperty> {
        return connectors.first { it.supportsRequest(request) }.processAuthRequest(request)
    }
}