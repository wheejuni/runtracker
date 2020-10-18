package com.wheejuni.runtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RuntrackerApplication

fun main(args: Array<String>) {
    runApplication<RuntrackerApplication>(*args)
}
