package me.modorigoon.springio.springdatajpa

import me.modorigoon.springio.springdatajpa.album.Album
import me.modorigoon.springio.springdatajpa.album.AlbumRepository
import me.modorigoon.springio.springdatajpa.photo.Photo
import me.modorigoon.springio.springdatajpa.photo.PhotoRepository
import me.modorigoon.springio.springdatajpa.user.User
import me.modorigoon.springio.springdatajpa.user.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor


@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AlbumRepositoryTest(val userRepository: UserRepository,
                          val albumRepository: AlbumRepository,
                          val photoRepository: PhotoRepository) {

    @Test
    fun `album repository tests`() {
        val user = userRepository.save(User("James", 1980))

        val photo1 = photoRepository.save(Photo("korea", 212311L, "img_1.png"))
        val photo2 = photoRepository.save(Photo("aus", 112511L, "img_2.png"))

        var album = Album(user)
        album.addPhoto(photo1)
        album.addPhoto(photo2)

        album = albumRepository.save(album)
        assertEquals(2, album.photos.size)

        val photo3 = photoRepository.save(Photo("nz", 5512111L, "img_3.png"))
        album.addPhoto(photo3)
        album = albumRepository.save(album)
        assertEquals(3, album.photos.size)
    }
}
