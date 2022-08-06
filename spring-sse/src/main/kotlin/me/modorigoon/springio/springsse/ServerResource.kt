package me.modorigoon.springio.springsse

import java.time.LocalDateTime


data class ServerResource(val cpuLoad: Double, val totalMem: Long, val freeMem: Long, val time: LocalDateTime)
