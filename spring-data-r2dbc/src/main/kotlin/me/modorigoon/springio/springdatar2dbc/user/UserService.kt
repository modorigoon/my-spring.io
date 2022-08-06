package me.modorigoon.springio.springdatar2dbc.user

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserService(val r2dbcEntityTemplate: R2dbcEntityTemplate) {

    fun saveUser(user: User): Mono<User> {
        return r2dbcEntityTemplate.insert(user)
    }

    fun findByName(name: String): Flux<User> {
        return r2dbcEntityTemplate.select(query(where("name").`is`(name)), User::class.java)
    }
}
