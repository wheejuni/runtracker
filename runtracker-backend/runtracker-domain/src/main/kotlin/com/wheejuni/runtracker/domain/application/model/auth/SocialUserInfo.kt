package com.wheejuni.runtracker.domain.application.model.auth

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/14
 */
interface SocialUserInfo {
    fun getUsername(): String
    fun getProviderUserId(): String
    fun getProviderType(): SocialProvider
}