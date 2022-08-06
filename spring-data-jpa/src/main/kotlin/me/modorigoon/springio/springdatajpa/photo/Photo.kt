package me.modorigoon.springio.springdatajpa.photo

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Photo(var name: String, var size: Long, var path: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    var createdAt: LocalDateTime? = null

    @CreationTimestamp
    fun onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now()
        }
    }
}
