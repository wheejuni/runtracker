package com.wheejuni.runtracker.domain.entity

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/14
 */
@Entity
data class RuntrackerUser(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val runtrackerUserId: Long,

    val username: String,

    val encryptedPassword: String? = null,

    val socialProviderId: String? = null,

    @Enumerated(EnumType.STRING)
    val socialProviderType: SocialProvider,

    val nickname: String
)
