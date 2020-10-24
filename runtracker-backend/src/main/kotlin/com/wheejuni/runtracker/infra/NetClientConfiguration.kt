package com.wheejuni.runtracker.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.LoggingCodecSupport
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

const val HTTP_CLIENT_MAX_MEMORY_IN_MB = 5 * 1024 * 1024

@Configuration
class NetClientConfiguration {

    @Bean
    fun defaultWebClient(): WebClient {
        val strategies = ExchangeStrategies.builder()
                .codecs { it.defaultCodecs().maxInMemorySize(HTTP_CLIENT_MAX_MEMORY_IN_MB) }
                .build()

        strategies.messageWriters()
                .filter { it is LoggingCodecSupport }
                .map { it as LoggingCodecSupport }
                .forEach { it.isEnableLoggingRequestDetails = true}

        return WebClient.builder()
                .exchangeStrategies(strategies)
                .build()
    }
}
