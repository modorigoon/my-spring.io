package me.modorigoon.springio.springdatar2dbc.book

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


interface BookRepository: ReactiveCrudRepository<Book, Long> {

    @Query("SELECT id, title, author, publication_at FROM book b WHERE b.title = :title")
    fun findByTitle(title: String): Flux<Book>
}
