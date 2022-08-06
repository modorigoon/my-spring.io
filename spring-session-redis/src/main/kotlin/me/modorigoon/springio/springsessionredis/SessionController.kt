package me.modorigoon.springio.springsessionredis

import org.springframework.data.redis.core.RedisOperations
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/session")
class SessionController(val redisTemplate: RedisOperations<String, String>) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun setName(session: HttpSession, @RequestParam(value = "name", required = true) name: String) : String {
        session.setAttribute("name", name)
        return session.id
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getName(@RequestParam(value = "sessionId", required = true) sessionId: String) : String? {
        return redisTemplate.opsForHash<String, String>().get("spring:session:sessions:$sessionId", "sessionAttr:name")
    }
}
