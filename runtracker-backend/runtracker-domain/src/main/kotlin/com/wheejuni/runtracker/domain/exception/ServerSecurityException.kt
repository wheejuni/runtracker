package com.wheejuni.runtracker.domain.exception


/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
class ServerSecurityException(private val errorMessage: String): RuntimeException(errorMessage) {
}