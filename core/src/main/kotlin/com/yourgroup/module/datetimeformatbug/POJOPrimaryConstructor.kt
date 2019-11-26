package com.yourgroup.module.datetimeformatbug

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class POJOPrimaryConstructor (
    var someField: String? = null,
    @field:DateTimeFormat(pattern = "yyyy.MM.dd") var someDate: Date? = null,
    @field:DateTimeFormat(pattern = "yyyy.MM.dd") var anotherDate: Date? = null,
    var someOtherField: String? = null
)