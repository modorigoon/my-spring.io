package me.modorigoon.springio.springdataredis.trade

import org.springframework.data.repository.CrudRepository


interface TradeRepository : CrudRepository<Trade, Long>
