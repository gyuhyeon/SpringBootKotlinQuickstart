package com.yourgroup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.yourgroup"])
class FrontendApplication

fun main(args: Array<String>) {
	runApplication<FrontendApplication>(*args)
}
