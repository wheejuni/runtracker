package com.wheejuni.runtracker.domain.application.model.auth

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/14
 */
data class LoginRequest(
    val id: String,
    val password: String
)

data class SocialLoginRequest(
    val authUserId: String,
    val provider: SocialProvider
)
