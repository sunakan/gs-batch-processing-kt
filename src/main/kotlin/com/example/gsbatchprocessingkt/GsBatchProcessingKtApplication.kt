package com.example.gsbatchprocessingkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GsBatchProcessingKtApplication

fun main(args: Array<String>) {
    runApplication<GsBatchProcessingKtApplication>(*args)
}
