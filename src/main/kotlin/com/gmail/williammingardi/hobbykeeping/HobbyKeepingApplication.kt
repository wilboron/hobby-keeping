package com.gmail.williammingardi.hobbykeeping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZoneOffset
import java.util.*

@SpringBootApplication
class HobbyKeepingApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC.id))
    runApplication<HobbyKeepingApplication>(*args)
}
