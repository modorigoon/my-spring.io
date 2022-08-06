package me.modorigoon.springio.springdatar2dbc.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import reactor.test.StepVerifier


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class StoreEntityCallbackTest(val storeRepository: StoreRepository) {

    @Test
    fun `store name uppercase`() {

        val name = "Red umbrellas"
        val address = "Sin-sa dong 12-12"
        val store = Store(null, name, address)

        storeRepository.save(store)
            .`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo(name.toUpperCase().replace(" ", "_"))
            }
            .verifyComplete()
    }
}
