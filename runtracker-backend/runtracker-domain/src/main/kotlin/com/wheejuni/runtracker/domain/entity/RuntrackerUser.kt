package com.wheejuni.runtracker.domain.entity

import com.wheejuni.runtracker.domain.application.model.auth.AuthProvider
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
@Table("runtracker_user")
data class RuntrackerUser(

    @Id
    val runtrackerUserId: Long = 0,

    val username: String,

    val encryptedPassword: String? = null,

    val socialProviderId: String? = null,

    val authProviderType: AuthProvider,

    val nickname: String
)
