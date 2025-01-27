package dev.skillcode.scapiimporter.utils

import org.springframework.http.*
import org.springframework.web.client.RestTemplate


object HttpClient {

    private val restTemplate = RestTemplate()

    fun makeRequest(url: String): ResponseEntity<String> {
        val entity = HttpEntity<String>(getDefaultHeaders())
        return restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
    }

    private fun getDefaultHeaders() = HttpHeaders().apply {
        accept = listOf(MediaType.APPLICATION_JSON)
        set(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36"
        )
    }
}
