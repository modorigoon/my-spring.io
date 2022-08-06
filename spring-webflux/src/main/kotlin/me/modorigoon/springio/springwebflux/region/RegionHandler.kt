package me.modorigoon.springio.springwebflux.region

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait


@Component
class RegionHandler(val regionRepository: RegionRepository) {

    suspend fun getRegion(req: ServerRequest) = regionRepository
        .findById(req.pathVariable("id").toUpperCase())
        .awaitFirstOrNull()?.let {
            ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        } ?: notFound().buildAndAwait()

    suspend fun getRegions(req: ServerRequest) = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyAndAwait(regionRepository.findAll().asFlow())
}