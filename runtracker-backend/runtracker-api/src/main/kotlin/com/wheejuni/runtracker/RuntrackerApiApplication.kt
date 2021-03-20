package com.wheejuni.runtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@SpringBootApplication
class RuntrackerApiApplication

fun main(args: Array<String>) {
    runApplication<RuntrackerApiApplication>(*args)
}