package com.wheejuni.runtracker.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class RuntrackerApiApplication

fun main(args: Array<String>) {
    runApplication<RuntrackerApiApplication>(*args)
}