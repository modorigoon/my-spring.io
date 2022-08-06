package me.modorigoon.springio.springdataredis.stock

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.data.redis.core.RedisOperations
import org.springframework.stereotype.Component


@Component
class StockCache(val stringRedisTemplate: RedisOperations<String, String>, val objectMapper: ObjectMapper) {

    companion object {
        fun generateCacheKey(id: Int): String {
            return String.format("STOCK:%s", id)
        }
    }

    fun set(id: String, stock: Stock) {
        stringRedisTemplate.opsForValue().set(id, objectMapper.writeValueAsString(stock))
    }

    fun get(id: String): Stock? {
        val s: String? = stringRedisTemplate.opsForValue().get(id)
        if (s == null || s == "") {
            return null
        }
        return objectMapper.readValue(s)
    }
}
