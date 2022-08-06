package me.modorigoon.springio.springsse

import org.springframework.stereotype.Service
import java.lang.management.ManagementFactory
import com.sun.management.OperatingSystemMXBean
import java.time.LocalDateTime


@Service
class ServerResourceService {

    private val osMXBean: OperatingSystemMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java)

    fun getResource(): ServerResource {
        return ServerResource(
            osMXBean.processCpuLoad,
            osMXBean.totalPhysicalMemorySize,
            osMXBean.freePhysicalMemorySize,
            LocalDateTime.now()
        )
    }
}