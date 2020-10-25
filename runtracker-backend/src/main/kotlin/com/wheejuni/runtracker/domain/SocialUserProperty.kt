package com.wheejuni.runtracker.domain

import com.fasterxml.jackson.annotation.JsonProperty

const val KAKAO_USER_INFO_ENDPOINT = "https://kapi.kakao.com/v2/user/me"

interface SocialUserProperty {
    fun getNickname(): String
    fun getUserCredential(): String
}

class KakaoUserProperty(
        val id: Long,

        @field:JsonProperty("connected_at")
        val connectedAt: String,

        val properties: Map<String, String>): SocialUserProperty {

    override fun getNickname(): String {
        return properties["nickname"] ?: "기본 사용자"
    }

    override fun getUserCredential(): String {
        return id.toString()
    }
}