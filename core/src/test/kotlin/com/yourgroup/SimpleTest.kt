package com.yourgroup

import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 26.
 */
@SpringBootTest
class SimpleTest {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun getEnv() {
        logger.info(System.getenv("nonexistingkey") ?: "default")
    }
}