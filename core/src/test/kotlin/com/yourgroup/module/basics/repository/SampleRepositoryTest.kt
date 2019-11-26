package com.yourgroup.module.basics.repository

import com.yourgroup.TestWithContexts
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 26.
 */
class SampleRepositoryTest: TestWithContexts() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired lateinit var repository: SampleRepository

    @Test
    fun selectOne() {
        logger.info(repository.selectOne(1).toString())
    }
}