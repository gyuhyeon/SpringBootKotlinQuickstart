package com.yourgroup.module.basics.service

import com.yourgroup.module.basics.domain.Sample
import com.yourgroup.module.basics.repository.SampleRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 25.
 */
@Service
class SampleService (
    private val repository: SampleRepository
) {
    @Value("\${example.prop.value}") // requires IntelliJ version higher than 2018.3-ish for value peek. Ctrl+"-" should show the correct value in the editor.
    private lateinit var propValue: String

    init {
        // changing @Value injected fields in init{} block will create an error since they aren't initialized yet. Do it in @PostConstruct instead
        propValue = "can't override in init block since @Value injection happens later"
    }

    @PostConstruct
    fun postInit() {
        //propValue = "override"
    }

    fun getPropValue(): String {
        return propValue
    }

    fun getRepositoryValue(id: Long): Sample {
        return repository.selectOne(id)
    }

    @Bean
    fun someBean(): Date {
        println("someBean - $propValue") // method @Bean instantiation comes after @PostConstruct
        return Date()
    }
}