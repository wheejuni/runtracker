package com.wheejuni.runtracker.domain.application.model.application

import org.springframework.data.annotation.Id

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/27
 */
data class Waypoint(

    @Id
    val waypointId: Long = 0L,

    val latitude: Double,
    val longitude: Double)
