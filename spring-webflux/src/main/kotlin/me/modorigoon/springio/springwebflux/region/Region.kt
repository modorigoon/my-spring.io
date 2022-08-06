package me.modorigoon.springio.springwebflux.region

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id


@Entity
data class Region(
    @Id var id: String,
    var name: String,
    var description: String,
    @CreationTimestamp var createdAt: LocalDateTime? = null
)
