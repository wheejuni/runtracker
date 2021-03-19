package com.wheejuni.runtracker.domain.exception

import org.springframework.security.core.AuthenticationException

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
class ServerSecurityException(private val errorMessage: String): AuthenticationException(errorMessage) {
}