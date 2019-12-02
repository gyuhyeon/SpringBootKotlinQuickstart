package com.yourgroup

import com.yourgroup.frontend.FrontendApplication
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [FrontendApplication::class])
class TestWithContexts
