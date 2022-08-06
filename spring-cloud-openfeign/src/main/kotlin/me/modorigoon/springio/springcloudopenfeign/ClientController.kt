package me.modorigoon.springio.springcloudopenfeign

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/client")
class ClientController(val greetingClient: GreetingClient) {

    @GetMapping("/greeting")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun greeting(): Greeting {
        return greetingClient.greeting("Modorigoon")
    }
}
