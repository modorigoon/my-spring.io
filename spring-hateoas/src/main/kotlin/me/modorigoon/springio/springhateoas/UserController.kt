package me.modorigoon.springio.springhateoas

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/{name}")
    fun userGreet(@PathVariable("name") name: String): ResponseEntity<User> {
        val user = User(name, "USER")
        user.add(linkTo(methodOn(UserController::class.java).userGreet(name)).withSelfRel())
        return ResponseEntity(user, HttpStatus.OK)
    }
}
