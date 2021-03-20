package com.wheejuni.runtracker.domain.entity

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider
import org.springframework.data.annotation.Id


/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
data class RuntrackerUser(

    @Id
    val runtrackerUserId: Long,

    val username: String,

    val encryptedPassword: String? = null,

    val socialProviderId: String? = null,

    val socialProviderType: SocialProvider,

    val nickname: String
)
