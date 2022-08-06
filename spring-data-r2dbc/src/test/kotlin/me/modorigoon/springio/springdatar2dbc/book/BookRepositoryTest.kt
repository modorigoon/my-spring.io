package me.modorigoon.springio.springdatar2dbc.book

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import reactor.test.StepVerifier
import java.time.LocalDateTime


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BookRepositoryTest(val bookRepository: BookRepository) {

    @Test
    fun `assert find title`() {

        val book1 = Book(null, "Reading for Today", "NG", LocalDateTime.now())
        val book2 = Book(null, "Nocturnal Animals and Classroom Nights", "McGraw", LocalDateTime.now())
        val books = arrayListOf(book1, book2)
        createBooks(books)

        bookRepository.findByTitle(book1.title)
            .`as`(StepVerifier::create)
            .assertNext { assertThat(it.title).isEqualTo(book1.title) }
            .verifyComplete()
    }

    fun createBooks(books: List<Book>) {
        bookRepository.saveAll(books).`as`(StepVerifier::create).expectNextCount(2).verifyComplete()
    }
}
