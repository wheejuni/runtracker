package com.wheejuni.runtracker.domain.entity.repository

import com.wheejuni.runtracker.domain.application.model.auth.AuthProvider
import com.wheejuni.runtracker.domain.entity.RuntrackerUser
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
interface RuntrackerUserRepository: R2dbcRepository<RuntrackerUser, Long> {

    fun findByRuntrackerUserId(id: Long): RuntrackerUser?
    fun findBySocialProviderIdAndAuthProviderType(socialProviderId: String, authProviderType: AuthProvider): Mono<RuntrackerUser>
    fun findByUsername(username: String): Mono<RuntrackerUser>
    fun countByUsername(username: String): Mono<Int>
}