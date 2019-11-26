package com.yourgroup

import com.yourgroup.config.CoreConfig
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [CoreConfig::class])
class TestWithContexts
