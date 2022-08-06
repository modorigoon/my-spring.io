package me.modorigoon.springio.springcloudaws

import java.time.LocalDateTime


data class ServerStatus(val name: String, val status: Status, val updatedAt: LocalDateTime)

enum class Status {
    ACTIVE, MAINTENANCE
}
