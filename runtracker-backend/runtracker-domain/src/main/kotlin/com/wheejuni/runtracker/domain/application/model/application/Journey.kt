package com.wheejuni.runtracker.domain.application.model.application

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/27
 */
data class Journey(

    @Id
    val journeyId: Long = 0L,

    val started: LocalDateTime,
    val ended: LocalDateTime,
    val length: Double)
