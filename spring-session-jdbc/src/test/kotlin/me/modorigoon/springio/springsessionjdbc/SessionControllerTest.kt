package me.modorigoon.springio.springsessionjdbc

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@AutoConfigureMockMvc
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SessionControllerTest(val context: WebApplicationContext) {

    lateinit var session: MockHttpSession
    lateinit var mockMvc: MockMvc

    val name: String = "Jane"


    @BeforeEach
    fun before() {
        session = MockHttpSession()
        session.setAttribute("name", name)
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @AfterEach
    fun after() {
        session.clearAttributes()
    }

    @Test
    fun `assert set session`() {
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/session?name=${name}"))
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `assert get session`() {
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/session").session(session))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(content().string(org.hamcrest.Matchers.containsString(name)))
    }
}
