package com.yourgroup.module.basics.domain

import java.time.LocalDateTime

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
data class Sample (
    var id: Long? = null,
    var text: String? = null,
    var date: LocalDateTime? = null
)