package me.modorigoon.springio.springdatajpa.log

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository


interface AccessLogRepository: JpaRepository<AccessLog, Long> {

    @CacheEvict(value = ["byHash"], key = "#p0.hash")
    override fun <S : AccessLog> save(entity: S) : S

    @Cacheable("byHash")
    fun findByHash(hash: String) : AccessLog
}
