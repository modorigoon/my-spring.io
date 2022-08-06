package me.modorigoon.springio.springcloudaws

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ServerStatusServiceTest(val serverStatusService: ServerStatusService) {

	@Test
	fun `object CRUD test`() {
		val serverName = "API"
		val updatedAt = LocalDateTime.now()

		val serverStatus = ServerStatus(serverName, Status.ACTIVE, updatedAt)
		serverStatusService.setServerStatus(serverStatus)

		val savedStatus = serverStatusService.getServerStatus(serverName)
		assertNotNull(savedStatus)
		assertEquals(serverName, savedStatus!!.name)
		assertEquals(Status.ACTIVE, savedStatus.status)
		assertEquals(updatedAt, savedStatus.updatedAt)

		serverStatusService.deleteServerStatus(serverName)

		val deletedStatus = serverStatusService.getServerStatus(serverName)
		assertNull(deletedStatus)
	}
}
