package dev.skillcode.scapiimporter

import dev.skillcode.scapiimporter.services.ImportService
import jakarta.annotation.PostConstruct
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

fun main(args: Array<String>) {
    SpringApplicationBuilder(ScApiImporterApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)
}

@SpringBootApplication
class ScApiImporterApplication(
    private val importService: ImportService
) {

    @PostConstruct
    fun init() {
        importService.importAll()
    }

}
