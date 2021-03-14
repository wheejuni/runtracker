package com.wheejuni.runtracker.domain.application.service

import com.wheejuni.runtracker.domain.application.infra.security.tokens.RuntrackerUserToken
import com.wheejuni.runtracker.domain.application.infra.security.tokens.SocialProviderLoginAuthenticationToken
import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider
import com.wheejuni.runtracker.domain.entity.repository.RuntrackerUserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/14
 */
@Service
class RuntrackerUserService(
    private val repository: RuntrackerUserRepository,
    private val encryptor: PasswordEncoder
) {

    fun getUserInfoForSocialLogin(token: SocialProviderLoginAuthenticationToken): Mono<RuntrackerUserToken> {
        return repository.findBySocialProviderIdAndSocialProviderType(
            socialProviderId = token.credentials,
            socialProviderType = token.requestedAuthenticationProvider
        ).flatMap {
            return@flatMap Mono.just(RuntrackerUserToken(it, listOf(SimpleGrantedAuthority("USER"))))
        }
    }

    fun getUserInfoForFormLogin(token: UsernamePasswordAuthenticationToken): Mono<RuntrackerUserToken> {
        return repository.findByUsername(token.principal as String).filter {
            this.encryptor.matches(token.credentials as String, it.encryptedPassword!!)
        }.flatMap { user -> Mono.just(RuntrackerUserToken(user, listOf(SimpleGrantedAuthority("USER")))) }
    }

}