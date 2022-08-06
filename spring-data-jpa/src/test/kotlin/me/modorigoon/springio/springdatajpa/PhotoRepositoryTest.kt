package me.modorigoon.springio.springdatajpa

import me.modorigoon.springio.springdatajpa.photo.Photo
import me.modorigoon.springio.springdatajpa.photo.PhotoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime


@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class PhotoRepositoryTest(val photoRepository: PhotoRepository) {

    @Test
    fun `find by name test`() {

        val name = "paris"
        val size = 1911221L
        val path = "/image/img_1.jpg"

        photoRepository.save(Photo(name, size, path))

        val photo : Photo = photoRepository.findTopByName(name)

        assertNotNull(photo)
        assertNotNull(photo.id)
        assertNotEquals(0, photo.id)
        assertEquals(name, photo.name)
        assertEquals(size, photo.size)
        assertEquals(path, photo.path)
    }

    @Test
    fun `find by created_at between test`() {

        val photo1 = Photo("paris", 10211L, "img_1.png")
        photo1.createdAt = LocalDateTime.of(2021, 1, 10, 13, 12, 0)
        photoRepository.save(photo1)

        val photo2 = Photo("tokyo", 9912311L, "img_2.png")
        photo2.createdAt = LocalDateTime.of(2021, 1, 15, 13, 12, 0)
        photoRepository.save(photo2)

        val photo3 = Photo("aus", 551231231L, "img_3.png")
        photo3.createdAt = LocalDateTime.of(2021, 2, 15, 13, 12, 0)
        photoRepository.save(photo3)

        val photos : List<Photo> = photoRepository.findByCreatedAtBetween(
            LocalDateTime.of(2021, 1, 1, 13, 12, 0),
            LocalDateTime.of(2021, 1, 30, 13, 12, 0)
        )

        assertNotNull(photos)
        assertFalse(photos.isEmpty())
        assertEquals(2, photos.size)

        photos.forEach { p ->
            p.createdAt?.let {
                assertTrue(
                    it.isAfter(LocalDateTime.of(2021, 1, 1, 0, 0, 0)) &&
                            it.isBefore(LocalDateTime.of(2021, 1, 30, 0, 0, 0))
                )
            }
        }
    }
}
