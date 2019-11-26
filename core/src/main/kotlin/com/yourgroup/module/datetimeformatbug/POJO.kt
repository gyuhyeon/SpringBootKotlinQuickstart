package com.yourgroup.module.datetimeformatbug

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 20.
 */
class POJO {
    @DateTimeFormat(pattern = "yyyy.MM.dd") var someDate: Date? = null
}