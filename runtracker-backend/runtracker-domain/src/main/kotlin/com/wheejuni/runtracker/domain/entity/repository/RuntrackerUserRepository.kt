package com.wheejuni.runtracker.domain.entity.repository

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider
import com.wheejuni.runtracker.domain.entity.RuntrackerUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
interface RuntrackerUserRepository: ReactiveCrudRepository<RuntrackerUser, Long> {

    fun findByRuntrackerUserId(id: Long): RuntrackerUser?
    fun findBySocialProviderIdAndSocialProviderType(socialProviderId: String, socialProviderType: SocialProvider): Mono<RuntrackerUser>
    fun findByUsername(username: String): Mono<RuntrackerUser>
    fun countByUsername(username: String): Mono<Int>
}