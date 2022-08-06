package me.modorigoon.springio.springdatajpa.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing
class AuditingConfiguration {

    @Bean
    fun auditorAware(): AuditorAwareImpl {
        return AuditorAwareImpl()
    }
}
