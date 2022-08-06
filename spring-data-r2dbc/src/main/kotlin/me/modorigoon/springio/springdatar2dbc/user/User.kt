package me.modorigoon.springio.springdatar2dbc.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("user")
data class User(@Id val id: Long?, val name: String, val birth: Int)
