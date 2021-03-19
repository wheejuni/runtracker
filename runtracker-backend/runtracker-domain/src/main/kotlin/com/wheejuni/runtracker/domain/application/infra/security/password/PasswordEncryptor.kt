package com.wheejuni.runtracker.domain.application.infra.security.password

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
interface PasswordEncryptor {
    fun encrypt(originalPassword: String): String
    fun matches(requestedPassword: String, encryptedPassword: String): String
}