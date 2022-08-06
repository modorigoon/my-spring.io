package me.modorigoon.springio.springcloudopenfeign

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping("/server")
class ServerController {

    @GetMapping("/greeting/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun greeting(@PathVariable(name = "name", required = true) name: String): Greeting {
        return Greeting(name, LocalDateTime.now())
    }
}
