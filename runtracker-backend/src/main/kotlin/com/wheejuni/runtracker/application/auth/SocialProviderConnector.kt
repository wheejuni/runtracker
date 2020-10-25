package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.ApplicationLoginRequest
import com.wheejuni.runtracker.domain.SocialUserProperty
import reactor.core.publisher.Mono

interface SocialProviderConnector<T: SocialUserProperty> {

    fun supportsRequest(request: ApplicationLoginRequest): Boolean
    fun processAuthRequest(request: ApplicationLoginRequest): Mono<T>
}