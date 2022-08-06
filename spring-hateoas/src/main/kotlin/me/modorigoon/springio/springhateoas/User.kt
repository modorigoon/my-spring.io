package me.modorigoon.springio.springhateoas

import org.springframework.hateoas.RepresentationModel


data class User(val name: String, val role: String): RepresentationModel<User>()
