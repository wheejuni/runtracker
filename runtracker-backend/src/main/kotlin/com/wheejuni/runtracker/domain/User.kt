package com.wheejuni.runtracker.domain

data class User(
        val id: Long = 0,
        val nickname: String = "",
        val username: String = "",
        val credential: Credential,
        val loginProvider: UserInfoProvider)

inline class Credential (private val credentialToken: String) {
    fun matches(loginRequestedPassword: String): Boolean = credentialToken == loginRequestedPassword
}