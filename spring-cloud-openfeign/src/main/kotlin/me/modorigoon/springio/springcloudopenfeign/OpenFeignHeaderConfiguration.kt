package me.modorigoon.springio.springcloudopenfeign

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


@Configuration
class OpenFeignHeaderConfiguration : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }
}
