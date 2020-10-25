package com.wheejuni.runtracker.application.auth

import com.wheejuni.runtracker.application.view.ApplicationLoginRequest
import com.wheejuni.runtracker.application.view.ApplicationLoginResponse
import com.wheejuni.runtracker.domain.User
import com.wheejuni.runtracker.domain.UserInfoProvider
import com.wheejuni.runtracker.domain.UserRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

val logger = LoggerFactory.getLogger(UserinfoManagementService::class.java)

@Service
class UserinfoManagementService(
        private val fetchService: SocialPropertyFetchService,
        private val repository: UserRepository) {

    fun processLoginRequest(request: ApplicationLoginRequest): Mono<ApplicationLoginResponse> {
        if(request.provider != UserInfoProvider.FORM_LOGIN) {
            return fetchService.retrieveSocialProperties(request)
                    .map { it.getUserCredential() }
                    .thenMany<User> {  }

        }
    }
}