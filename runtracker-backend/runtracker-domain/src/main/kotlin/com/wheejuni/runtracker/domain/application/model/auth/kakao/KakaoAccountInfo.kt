package com.wheejuni.runtracker.domain.application.model.auth.kakao

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
data class KakaoAccountInfo(
    val profile: KakaoProfile,
    val email: String,
    val ageRange: String,
    val birthday: String,
    val birthYear: String,
    val gender: String,
    val phoneNumber: String,
    val ci: String? = null
    )

data class KakaoProfile(
    val nickname: String,
    val profileImage: String,
    val thumbnailImageUrl: String,
    val profileNeedsAgreement: Boolean
)
