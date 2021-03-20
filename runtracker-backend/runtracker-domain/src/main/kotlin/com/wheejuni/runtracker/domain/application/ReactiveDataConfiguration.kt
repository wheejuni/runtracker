package com.wheejuni.runtracker.domain.application

import com.wheejuni.runtracker.domain.application.model.auth.AuthProvider
import com.wheejuni.runtracker.domain.entity.RuntrackerUser
import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration
import dev.miku.r2dbc.mysql.MySqlConnectionFactory
import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@Configuration
@EntityScan(basePackages = ["com.wheejuni.runtracker.domain.entity"])
@EnableR2dbcRepositories(basePackages = ["com.wheejuni.runtracker.domain.entity.repository"])
class ReactiveDataConfiguration: AbstractR2dbcConfiguration() {

    @Bean(name = ["h2ConnectionFactory"])
    override fun connectionFactory(): ConnectionFactory =
        MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
            .host("localhost")
            .port(3306)
            .user("root")
            .password("bomeehouse")
            .database("runtracker")
            .build())

    @Bean
    fun r2dbcTemplate(): R2dbcEntityTemplate = R2dbcEntityTemplate(connectionFactory())

    @Bean
    fun initializeDb(): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        val populator = ResourceDatabasePopulator(ClassPathResource("schema.sql"))

        initializer.setConnectionFactory(connectionFactory())
        initializer.setDatabasePopulator(populator)

        return initializer
    }
}