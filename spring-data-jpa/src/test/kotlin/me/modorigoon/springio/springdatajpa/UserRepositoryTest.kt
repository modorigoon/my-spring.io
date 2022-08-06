package me.modorigoon.springio.springdatajpa

import me.modorigoon.springio.springdatajpa.user.AuditorAwareImpl
import me.modorigoon.springio.springdatajpa.user.User
import me.modorigoon.springio.springdatajpa.user.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserRepositoryTest(val userRepository: UserRepository, val auditorAware: AuditorAwareImpl) {

    @Test
    fun `user auditing test`() {

        var user = User("Jane", 1980)
        auditorAware.setAuditor(user)
        user = userRepository.save(user)

        user.birth = 1981
        user = userRepository.save(user)

        user.birth = 1982
        user = userRepository.save(user)

        assertEquals(1981, user.updatedBy?.birth)
        assertEquals(1982, user.birth)
    }
}
