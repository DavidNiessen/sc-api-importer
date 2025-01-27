package dev.skillcode.scapiimporter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class Beans {

    @Bean
    fun objectMapper() = ObjectMapper()

}
