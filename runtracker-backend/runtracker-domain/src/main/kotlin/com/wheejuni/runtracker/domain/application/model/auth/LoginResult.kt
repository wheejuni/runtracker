package com.wheejuni.runtracker.domain.application.model.auth

/**
 *  Bomeehouse studios (me@wheejuni.com)
 *  2021/03/19
 */
data class LoginResult(
    val status: String,
    val token: String,
    val refreshToken: String)
