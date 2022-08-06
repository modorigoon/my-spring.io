package me.modorigoon.springio.springdatajpa.user

import org.springframework.data.domain.AuditorAware
import java.util.*


class AuditorAwareImpl: AuditorAware<User> {

    private var auditor: Optional<User> = Optional.empty()

    fun setAuditor(auditor: User) {
        this.auditor = Optional.of(auditor)
    }

    override fun getCurrentAuditor(): Optional<User> {
        return this.auditor
    }
}
