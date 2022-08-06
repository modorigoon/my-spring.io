package me.modorigoon.springio.springsse

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class WebPageController {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}
