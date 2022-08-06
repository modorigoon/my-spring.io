package me.modorigoon.springio.springdatar2dbc.store

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("store")
data class Store(@Id var id: Long?, var name: String, var address: String)
