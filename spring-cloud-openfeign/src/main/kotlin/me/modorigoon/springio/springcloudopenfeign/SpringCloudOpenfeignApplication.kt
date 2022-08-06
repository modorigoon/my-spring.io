package me.modorigoon.springio.springcloudopenfeign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@EnableFeignClients
@SpringBootApplication
class SpringCloudOpenfeignApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudOpenfeignApplication>(*args)
}
