package me.modorigoon.springio.springdatajpa.user

import org.springframework.data.repository.CrudRepository


interface UserRepository: CrudRepository<User, Long>
