package me.modorigoon.springio.springdatar2dbc.book

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("book")
class Book(@Id var id: Long?, var title: String, var author: String, var publicationAt: LocalDateTime)
