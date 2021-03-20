package com.wheejuni.runtracker.domain.application.model.auth

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
data class LoginRequest(
    val id: String,
    val password: String
)

data class SocialLoginRequest(
    val authUserId: String,
    val provider: AuthProvider
)
