package me.modorigoon.springio.springdatar2dbc.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import reactor.test.StepVerifier


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceTest(val userService: UserService) {

    @Test
    fun `create and get user`() {
        val user = User(null, "Jane", 1990)
        userService.saveUser(user)
            .`as`(StepVerifier::create)
            .assertNext { assertThat(it.name).isEqualTo(user.name) }
            .verifyComplete()

        userService.findByName(user.name)
            .`as`(StepVerifier::create)
            .assertNext { assertThat(it.birth).isEqualTo(user.birth) }
            .verifyComplete()
    }
}
