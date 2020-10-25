package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.ApplicationLoginRequest
import com.wheejuni.runtracker.domain.KakaoUserProperty
import com.wheejuni.runtracker.domain.UserInfoProvider
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class KakaoProviderConnector(private val webClient: WebClient): SocialProviderConnector<KakaoUserProperty> {

    override fun supportsRequest(request: ApplicationLoginRequest) = request.provider == UserInfoProvider.KAKAO

    override fun processAuthRequest(request: ApplicationLoginRequest): Mono<KakaoUserProperty> {
        return webClient.get()
                .uri(request.provider.userinfoEndpoint)
                .header("Content-Type")
                .header("Authorization", "Bearer ${request.accessToken}")
                .retrieve()
                .bodyToMono(KakaoUserProperty::class.java)
    }
}
