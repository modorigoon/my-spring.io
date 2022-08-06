package me.modorigoon.springio.springdatajpa

import me.modorigoon.springio.springdatajpa.log.AccessLog
import me.modorigoon.springio.springdatajpa.log.AccessLogRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@Transactional
@SpringBootTest
@ExtendWith(SpringExtension::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AccessLogRepositoryTest(val accessLogRepository: AccessLogRepository, val cacheManager: CacheManager) {

    @Test
    fun `assert cache value`() {
        val hash = "dummy_hash"
        var accessLog = AccessLog(hash, "added photo by jack.")

        accessLog = accessLogRepository.save(accessLog)
        assertThat(accessLogRepository.findByHash(hash)).isEqualTo(accessLog)

        val cache : Cache? = cacheManager.getCache("byHash")
        assertThat(cache?.get(hash)?.get()).isEqualTo(accessLog)
    }

    @Test
    fun `assert cache eviction`() {
        val hash = "dummy_hash"
        AccessLog(hash, "added photo by jack.")

        val cache : Cache? = cacheManager.getCache("byHash")
        assertThat(cache?.get(hash)?.get()).isEqualTo(null)
    }
}
