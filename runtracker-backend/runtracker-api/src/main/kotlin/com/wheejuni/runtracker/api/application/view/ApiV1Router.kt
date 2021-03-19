package com.wheejuni.runtracker.api.application.view

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/20
 */
@Configuration
@EnableWebFlux
class ApiV1Router {

    @Bean
    fun routes(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(GET("/hello"), HandlerFunction<ServerResponse> {
            ServerResponse.ok().body(fromObject("hellow world")) } )
    }
}