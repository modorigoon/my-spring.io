package me.modorigoon.springio.springdatajpa.photo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime


interface PhotoRepository: JpaRepository<Photo, Long> {

    fun findTopByName(name: String): Photo

    @Query("SELECT p FROM Photo p WHERE p.createdAt >= :start AND p.createdAt <= :end")
    fun findByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<Photo>
}
