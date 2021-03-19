package com.wheejuni.runtracker.domain.application.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@Configuration
class JSONSerializeConfiguration {

    @Bean
    fun defaultObjectMapper(): ObjectMapper =
        Jackson2ObjectMapperBuilder.json()
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .build()

}