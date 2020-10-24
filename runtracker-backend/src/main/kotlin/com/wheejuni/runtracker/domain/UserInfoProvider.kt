package com.wheejuni.runtracker.domain

enum class UserInfoProvider(
        val userinfoEndpoint: String) {

    APPLE(""),
    KAKAO("https://kapi.kakao.com/v2/user/me"),
    NAVER(""),
    FORM_LOGIN("")
}