package me.modorigoon.springio.springsessionredis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringSessionRedisApplication

fun main(args: Array<String>) {
    runApplication<SpringSessionRedisApplication>(*args)
}
