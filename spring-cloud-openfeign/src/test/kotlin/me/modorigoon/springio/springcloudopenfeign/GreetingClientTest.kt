package me.modorigoon.springio.springcloudopenfeign

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class GreetingClientTest(val greetingClient: GreetingClient) {

    @Test
    fun `greeting client test`() {
        val greeting = greetingClient.greeting("Dave")
        assertEquals("Dave", greeting.name)
        assertNotNull(greeting.time)
    }
}
