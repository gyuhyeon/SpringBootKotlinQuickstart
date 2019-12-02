package com.yourgroup.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 27.
 */
@Configuration
@PropertySource("classpath:properties/core.properties", "classpath:properties/db.properties")
class CorePropertiesConfig