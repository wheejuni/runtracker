package com.wheejuni.runtracker.application.view

import com.wheejuni.runtracker.domain.UserInfoProvider

data class ApplicationLoginRequest(
        val username: String,
        val password: String,
        val provider: UserInfoProvider,
        val accessToken: String)

data class ApplicationLoginResponse(
        val status: Int,
        val applicationToken: String)
