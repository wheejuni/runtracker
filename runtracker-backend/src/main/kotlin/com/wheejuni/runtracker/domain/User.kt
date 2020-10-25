package com.wheejuni.runtracker.domain

import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.relational.core.mapping.Table

@Table("USER")
data class User(
        val id: Long = 0,
        val nickname: String = "",
        val username: String = "",
        val credential: Credential,
        val loginProvider: UserInfoProvider,
        val socialIdentity: String?) {

    @PersistenceConstructor
    constructor(id: Long): this(
            id = id,
            credential = Credential.emptyCredential(),
            loginProvider = UserInfoProvider.FORM_LOGIN,
            socialIdentity = null)

    fun isMatchingSocialAuthentication(provider: UserInfoProvider) = loginProvider == provider
}

inline class Credential (private val credentialToken: String) {
    fun matches(loginRequestedPassword: String): Boolean = credentialToken == loginRequestedPassword

    companion object {

        @JvmStatic
        fun emptyCredential(): Credential {
            return Credential("")
        }
    }
}
