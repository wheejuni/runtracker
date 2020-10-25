package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.auth.token.UserinfoJwtGenerator
import com.wheejuni.runtracker.application.view.ApplicationLoginRequest
import com.wheejuni.runtracker.application.view.ApplicationLoginResponse
import com.wheejuni.runtracker.domain.UserInfoProvider
import com.wheejuni.runtracker.domain.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

val logger: Logger = LoggerFactory.getLogger(UserinfoManagementService::class.java)

const val AUTHENTICATION_SUCCESS_STATUS = 200
const val AUTHENTICATION_USER_FAILURE_STATUS = 403
const val AUTHENTICATION_INTERNAL_ERROR_STATUS = 500

@Service
class UserinfoManagementService(
        private val fetchService: SocialPropertyFetchService,
        private val repository: UserRepository,
        private val jwtGenerator: UserinfoJwtGenerator) {

    fun processLoginRequest(request: ApplicationLoginRequest): Mono<ApplicationLoginResponse> {
        if(request.provider != UserInfoProvider.FORM_LOGIN) {
             return fetchService.retrieveSocialProperties(request)
                     .map { it.getUserCredential() }
                     .flatMap { repository.findBySocialIdentity(it) }
                     .filter { it.isMatchingSocialAuthentication(request.provider) }
                     .switchIfEmpty(Mono.error(AuthenticationNotMatchingException("bad request")))
                     .flatMap { jwtGenerator.toJwt(it) }
                     .map { ApplicationLoginResponse(AUTHENTICATION_SUCCESS_STATUS, it) }
                     .doOnError(
                             AuthenticationNotMatchingException::class.java
                     ) {
                         logger.info("invalid login attempt made with social provider: ${request.provider}, accessToken: ${request.accessToken}")
                         ApplicationLoginResponse(AUTHENTICATION_USER_FAILURE_STATUS, "")
                     }
        }

        return Mono.empty()
    }
}

class AuthenticationNotMatchingException(override val message: String): RuntimeException(message)
