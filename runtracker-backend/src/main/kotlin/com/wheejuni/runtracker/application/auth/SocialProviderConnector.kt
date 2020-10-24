package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.SocialLoginRequest
import com.wheejuni.runtracker.domain.SocialUserProperty
import reactor.core.publisher.Mono

interface SocialProviderConnector<T: SocialUserProperty> {

    fun supportsRequest(request: SocialLoginRequest): Boolean
    fun processAuthRequest(request: SocialLoginRequest): Mono<T>
}