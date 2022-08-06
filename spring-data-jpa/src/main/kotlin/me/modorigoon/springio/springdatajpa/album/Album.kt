package me.modorigoon.springio.springdatajpa.album

import me.modorigoon.springio.springdatajpa.photo.Photo
import me.modorigoon.springio.springdatajpa.user.User
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class Album(@OneToOne @JoinColumn(name = "user_id") var user: User) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    var photos = mutableListOf<Photo>()

    @CreationTimestamp
    var createdAt: LocalDateTime? = null

    fun addPhoto(photo: Photo) {
        photos.plusAssign(photo)
    }
}
