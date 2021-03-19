package com.wheejuni.runtracker.domain.application.model.auth.kakao

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider
import com.wheejuni.runtracker.domain.application.model.auth.SocialUserInfo

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
class KakaoUserInfo(
    private val id: Long,
    private val kakaoAccount: KakaoAccountInfo,
    private val synchedAt: String,
    private val connectedAt: String,
    private val properties: Map<String, String>
): SocialUserInfo {

    override fun getUsername(): String = this.kakaoAccount.profile.nickname

    override fun getProviderUserId(): String = this.id.toString()

    override fun getProviderType(): SocialProvider = SocialProvider.KAKAO
}