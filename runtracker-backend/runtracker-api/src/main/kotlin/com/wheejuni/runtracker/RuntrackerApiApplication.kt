package com.wheejuni.runtracker

import com.wheejuni.runtracker.domain.application.model.auth.AuthProvider
import com.wheejuni.runtracker.domain.entity.RuntrackerUser
import com.wheejuni.runtracker.domain.entity.repository.RuntrackerUserRepository
import io.r2dbc.spi.ConnectionFactory
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.security.crypto.password.PasswordEncoder

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@SpringBootApplication
class RuntrackerApiApplication {
    val logger = KotlinLogging.logger { }

    @Bean
    fun dataInitializer(
        @Qualifier("r2dbcTemplate") template: R2dbcEntityTemplate,
        passwordEncoder: PasswordEncoder,
        userRepository: RuntrackerUserRepository): CommandLineRunner = CommandLineRunner {

        val user = RuntrackerUser(
            username = "bomeehouse",
            encryptedPassword = passwordEncoder.encode("bomee123"),
            authProviderType = AuthProvider.FORMLOGIN,
            nickname = "봄이네집")

        userRepository.save(user).subscribe()
    }
}

fun main(args: Array<String>) {
    runApplication<RuntrackerApiApplication>(*args)
}