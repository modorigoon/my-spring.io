package me.modorigoon.springio.springdataredis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "redis")
class RedisProperties {
    lateinit var host: String
    var port: Int = 0
    var database: Int = 0
}
