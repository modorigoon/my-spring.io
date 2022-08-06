package me.modorigoon.springio.springdatajpa.album

import org.springframework.data.jpa.repository.JpaRepository


interface AlbumRepository: JpaRepository<Album, Long>
