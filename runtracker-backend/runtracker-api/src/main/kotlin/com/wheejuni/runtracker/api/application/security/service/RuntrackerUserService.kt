package com.wheejuni.runtracker.api.application.security.service

import com.wheejuni.runtracker.api.application.security.tokens.RuntrackerUserToken
import com.wheejuni.runtracker.api.application.security.tokens.SocialProviderLoginAuthenticationToken
import com.wheejuni.runtracker.domain.entity.repository.RuntrackerUserRepository
import com.wheejuni.runtracker.domain.exception.ServerSecurityException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 *  Bomeehouse studios (me@wheejuni.com)
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

    fun getUserInfoByUserId(id: Long): Mono<RuntrackerUserToken>? {
        return Mono.just(id)
            .map repositorySearch@{
                return@repositorySearch repository.findByRuntrackerUserId(it) ?:
                    throw ServerSecurityException("userid에 맞는 사용자 없음")
            }
            .map { RuntrackerUserToken(it, listOf(SimpleGrantedAuthority("USER"))) }
            .onErrorContinue { _, _ -> null }
    }
}