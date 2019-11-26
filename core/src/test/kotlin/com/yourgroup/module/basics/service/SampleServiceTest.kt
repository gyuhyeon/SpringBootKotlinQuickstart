package com.yourgroup.module.basics.service

import com.yourgroup.TestWithContexts
import org.junit.Assert
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */

class SampleServiceTest: TestWithContexts() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var sampleService: SampleService

    @Test
    fun isValueLoaded() {
        Assert.assertTrue(sampleService.getPropValue().isNotEmpty())
        logger.info(sampleService.getPropValue())
    }
}