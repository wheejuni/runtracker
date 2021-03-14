package com.wheejuni.runtracker.domain.application.infra.security.password

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/14
 */
interface PasswordEncryptor {
    fun encrypt(originalPassword: String): String
    fun matches(requestedPassword: String, encryptedPassword: String): String
}