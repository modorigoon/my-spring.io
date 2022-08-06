package me.modorigoon.springio.springwebflux

import me.modorigoon.springio.springwebflux.region.RegionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class RouterConfiguration {

    @Bean
    fun regionRouter(regionHandler: RegionHandler) = coRouter {

        "/regions".nest {
            GET("/{id}", regionHandler::getRegion)
            GET("", regionHandler::getRegions)
        }
    }
}
