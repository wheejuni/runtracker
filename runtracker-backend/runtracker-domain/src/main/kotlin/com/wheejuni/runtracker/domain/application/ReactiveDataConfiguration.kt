package com.wheejuni.runtracker.domain.application

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@Configuration
@EntityScan(basePackages = ["com.wheejuni.runtracker.domain.entity"])
@EnableR2dbcRepositories(basePackages = ["com.wheejuni.runtracker.domain.entity.repository"])
class ReactiveDataConfiguration: AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory = ConnectionFactories.get("r2dbc:h2:mem:runtracker-test")
}