package com.wheejuni.runtracker.application.view

import com.wheejuni.runtracker.domain.SocialProvider

data class SocialLoginRequest(
        val provider: SocialProvider,
        val accessToken: String)