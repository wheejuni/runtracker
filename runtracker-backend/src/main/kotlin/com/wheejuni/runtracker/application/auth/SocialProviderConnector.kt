package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.SocialLoginRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class SocialProviderConnector(private val webClient: WebClient) {

    fun processSocialAuthRequest(request: SocialLoginRequest) {

    }

}
