package me.modorigoon.springio.springdatajpa.log

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@EnableCaching
@Configuration
class CachingConfiguration {

    @Bean
    fun cacheManager(): CacheManager {
        val cache = ConcurrentMapCache("byHash")
        val manager = SimpleCacheManager()
        manager.setCaches(listOf(cache))
        return manager
    }
}
