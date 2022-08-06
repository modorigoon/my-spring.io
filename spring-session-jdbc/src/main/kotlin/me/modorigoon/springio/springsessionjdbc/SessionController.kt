package me.modorigoon.springio.springsessionjdbc

import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpSession
import javax.xml.bind.DatatypeConverter


@Controller
@RequestMapping("/session")
class SessionController(val jdbcTemplate: JdbcTemplate) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun setName(session: HttpSession, @RequestParam("name") name: String) : String {
        session.setAttribute("name", name)
        return session.id
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getName(session: HttpSession) : String {
        return session.getAttribute("name").toString()
    }

    @GetMapping("/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getNameBySessionId(@PathVariable("sessionId", required = true) sessionId: String) : String {
        var name = ""
        val sql = """
            SELECT 
                A.ATTRIBUTE_BYTES 
            FROM 
                SPRING_SESSION S
            INNER JOIN
                SPRING_SESSION_ATTRIBUTES A
            ON S.PRIMARY_ID = A.SESSION_PRIMARY_ID
            WHERE
                    S.SESSION_ID = '$sessionId'
                AND A.ATTRIBUTE_NAME = 'name'
        """.trimIndent()
        kotlin.runCatching {
            jdbcTemplate.queryForObject(sql, String::class.java)
        }.onSuccess {
            name = String(DatatypeConverter.parseHexBinary(it.toString()), StandardCharsets.UTF_8)
        }
        return name
    }
}
