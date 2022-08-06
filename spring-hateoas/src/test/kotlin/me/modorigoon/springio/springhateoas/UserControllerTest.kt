package me.modorigoon.springio.springhateoas

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@ExtendWith(SpringExtension::class)
@WebMvcTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserControllerTest(private val mockMvc: MockMvc) {

    @Test
    fun `greeting controller self link test`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/user/jane"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$._links.self.href").value(Matchers.containsString("jane")))
    }
}
