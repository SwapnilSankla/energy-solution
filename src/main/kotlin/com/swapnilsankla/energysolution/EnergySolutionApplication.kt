package com.swapnilsankla.energysolution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.server.Shutdown

@SpringBootApplication
class EnergySolutionApplication

fun main(args: Array<String>) {
    runApplication<EnergySolutionApplication>(*args)
}

