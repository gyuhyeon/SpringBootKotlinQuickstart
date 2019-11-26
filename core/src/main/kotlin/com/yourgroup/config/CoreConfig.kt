package com.yourgroup.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
@ComponentScan(basePackages = ["com.yourgroup"], useDefaultFilters = false,
    includeFilters = [
        ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Service::class]),
        ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Component::class]),
        ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Repository::class]),
        ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Configuration::class])
    ])
@Configuration
@PropertySource("classpath:properties/core.properties", "classpath:properties/db.properties")
class CoreConfig