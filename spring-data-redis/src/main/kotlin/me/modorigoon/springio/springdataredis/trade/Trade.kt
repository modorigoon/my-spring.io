package me.modorigoon.springio.springdataredis.trade

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime


@RedisHash("Trade")
data class Trade(@Id val id: Long, val trader: Trader, val price: Long, val volume: Long, val time: LocalDateTime)
