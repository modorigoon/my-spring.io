package me.modorigoon.springio.springsse

import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration


@RestController
@RequestMapping("/server")
class ResourceController(val serverResourceService: ServerResourceService) {

    @GetMapping("/resource")
    fun getResource(): Flux<ServerSentEvent<ServerResource>> {
        return Flux.interval(Duration.ofSeconds(1))
            .map {
                ServerSentEvent.builder(serverResourceService.getResource()).build()
            }
    }
}
