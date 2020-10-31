package com.wheejuni.runtracker.application.view

import com.wheejuni.runtracker.domain.UserInfoProvider

data class ApplicationLoginRequest(
        val username: String,
        val password: String,
        val provider: UserInfoProvider,
        val accessToken: String) {

    fun isFormLoginRequest(): Boolean = username.isNotEmpty() && password.isNotEmpty()
}

data class ApplicationLoginResponse(
        val status: Int,
        val applicationToken: String)
