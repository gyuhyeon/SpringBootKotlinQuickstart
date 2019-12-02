package com.yourgroup.frontend

import com.yourgroup.config.CoreConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [CoreConfig::class], scanBasePackages = ["com.yourgroup.frontend"], exclude = [DataSourceAutoConfiguration::class, DataSourceTransactionManagerAutoConfiguration::class])
class FrontendApplication

fun main(args: Array<String>) {
	runApplication<FrontendApplication>(*args)
}
