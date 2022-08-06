package me.modorigoon.springio.springsecurityweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringSecurityWebApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityWebApplication>(*args)
}
