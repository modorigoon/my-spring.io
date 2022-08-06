package me.modorigoon.springio.springcloudopenfeign

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.management.Query


@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class GreetingControllerTest(private val mockMvc: MockMvc) {

    @Test
    fun `server greeting controller test`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/server/greeting/Jane").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                MockRestRequestMatchers.jsonPath("$[0].name", { Query.value("Jane") })
            }
    }

    @Test
    fun `client greeting controller test`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/client/greeting").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                MockRestRequestMatchers.jsonPath("$[0].name", { Query.value("Modorigoon") })
            }
    }
}
