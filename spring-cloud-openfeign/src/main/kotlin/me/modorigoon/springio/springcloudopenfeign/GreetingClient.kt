package me.modorigoon.springio.springcloudopenfeign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(value = "server", url = "http://\${server.address}:\${server.port}/server")
interface GreetingClient {

    @GetMapping("/greeting/{name}")
    fun greeting(@PathVariable(value = "name") name: String): Greeting
}
