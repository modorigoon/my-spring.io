package me.modorigoon.springio.springquickstart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringQuickStartApplication

fun main(args: Array<String>) {
	runApplication<SpringQuickStartApplication>(*args)
}
