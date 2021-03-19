package com.wheejuni.runtracker.domain.application

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 *  Created by quo.barlow@kakaocorp.com(정휘준)
 *  2021/03/19
 */
@EntityScan(basePackages = ["com.wheejuni.runtracker.domain.entity"])
@EnableJpaRepositories(basePackages = ["com.wheejuni.runtracker.domain.entity.repository"])
class DataJpaConfiguration {
}