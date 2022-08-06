package me.modorigoon.springio.springdatar2dbc.store

import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface StoreRepository: ReactiveCrudRepository<Store, Long>
