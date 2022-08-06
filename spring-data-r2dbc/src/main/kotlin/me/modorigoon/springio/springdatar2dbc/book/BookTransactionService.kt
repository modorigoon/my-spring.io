package me.modorigoon.springio.springdatar2dbc.book

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Service
class BookTransactionService(val bookRepository: BookRepository) {

    @Transactional
    fun save(book: Book): Mono<Book>? {
        return bookRepository.save(book).map {
            if (it.publicationAt.isAfter(LocalDateTime.now())) {
                throw IllegalStateException()
            }
            it
        }
    }
}
