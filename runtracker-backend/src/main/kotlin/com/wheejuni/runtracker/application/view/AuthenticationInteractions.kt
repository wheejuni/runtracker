package com.wheejuni.runtracker.application.view

import com.wheejuni.runtracker.domain.UserInfoProvider

data class SocialLoginRequest(
        val provider: UserInfoProvider,
        val accessToken: String)