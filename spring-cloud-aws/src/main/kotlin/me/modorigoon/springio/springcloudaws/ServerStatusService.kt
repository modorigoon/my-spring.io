package me.modorigoon.springio.springcloudaws

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


@Service
class ServerStatusService(val amazonS3Client: AmazonS3Client, val objectMapper: ObjectMapper) {

    @Value("\${cloud.aws.s3.bucket}")
    val bucket: String? = null

    private fun objectKeyOf(serverName: String): String {
        return "$serverName.json"
    }

    fun setServerStatus(serverStatus: ServerStatus) {
        val key = objectKeyOf(serverStatus.name)
        val bytes: ByteArray = objectMapper.writeValueAsBytes(serverStatus)
        val meta = ObjectMetadata()
        meta.contentLength = bytes.size.toLong()

        val inputStream = ByteArrayInputStream(bytes)
        inputStream.use {
            val request = PutObjectRequest(bucket, key, inputStream, meta)
            request.cannedAcl = CannedAccessControlList.PublicRead
            amazonS3Client.putObject(request)
        }
    }

    fun getServerStatus(name: String): ServerStatus? {
        kotlin.runCatching {
            amazonS3Client.getObject(bucket, objectKeyOf(name))
        }.onSuccess {
            return objectMapper.readValue(StreamUtils.copyToString(it.objectContent, StandardCharsets.UTF_8))
        }
        return null
    }

    fun deleteServerStatus(name: String) {
        amazonS3Client.deleteObject(bucket, objectKeyOf(name))
    }
}
