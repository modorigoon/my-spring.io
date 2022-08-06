package me.modorigoon.springio.springwebflux.region

import org.springframework.data.r2dbc.repository.R2dbcRepository


interface RegionRepository : R2dbcRepository<Region, String>
