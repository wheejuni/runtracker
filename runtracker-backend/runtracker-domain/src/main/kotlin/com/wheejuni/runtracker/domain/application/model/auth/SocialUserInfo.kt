package com.wheejuni.runtracker.domain.application.model.auth

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
interface SocialUserInfo {
    fun getUsername(): String
    fun getProviderUserId(): String
    fun getProviderType(): AuthProvider
}