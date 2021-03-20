package com.wheejuni.runtracker.api.application.security.password

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
interface PasswordEncryptor {
    fun encrypt(originalPassword: String): String
    fun matches(requestedPassword: String, encryptedPassword: String): String
}