package me.modorigoon.springio.springdataredis

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import java.util.Optional


@Configuration
class EmbeddedRedisServerConfiguration(val redisProperties: RedisProperties) : InitializingBean, DisposableBean {

    lateinit var redisServer: RedisServer

    override fun afterPropertiesSet() {
        redisServer = RedisServer(redisProperties.port)
        redisServer.start()
    }

    override fun destroy() {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop)
    }
}
