package com.giwankim.outbox.namastack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NamastackApplication

fun main(args: Array<String>) {
    runApplication<NamastackApplication>(*args)
}
