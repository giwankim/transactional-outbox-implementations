package com.giwankim.outbox.modulith

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ModulithApplication

fun main(args: Array<String>) {
    runApplication<ModulithApplication>(*args)
}
