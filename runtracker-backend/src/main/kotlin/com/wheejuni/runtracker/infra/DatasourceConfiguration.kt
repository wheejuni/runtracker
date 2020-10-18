package com.wheejuni.runtracker.infra

import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class DatasourceConfiguration(@Qualifier("connectionFactory") private val connectionFactory: ConnectionFactory) {

    @Bean
    fun getDatabaseClient(): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }
}