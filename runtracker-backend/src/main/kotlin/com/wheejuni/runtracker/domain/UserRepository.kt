package com.wheejuni.runtracker.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository: ReactiveCrudRepository<User, Long> {

    fun findBySocialIdentity(identity: String): Mono<User>
    fun findByUsername(username: String): Mono<User>
}