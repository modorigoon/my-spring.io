package me.modorigoon.springio.springsecurityweb

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class WebPageTest(private val mvc: MockMvc) {

    @Test
    fun `index page`() {
        mvc.perform(
            get("/index"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Welcome!")))
    }

    @Test
    @WithMockUser("user")
    fun `hello page`() {
        mvc.perform(
            get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Hello user!")))
    }
}
