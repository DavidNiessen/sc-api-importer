package dev.skillcode.scapiimporter.importers.ship

import com.fasterxml.jackson.databind.ObjectMapper
import dev.skillcode.scapiimporter.exceptions.InvalidResponseException
import dev.skillcode.scapiimporter.exceptions.UnexpectedValueException
import dev.skillcode.scapiimporter.importers.ship.converter.JsonToShipConverter
import dev.skillcode.scapiimporter.importers.ship.dto.ShipApiResponse
import dev.skillcode.scapiimporter.importers.ship.entity.ShipEntity
import dev.skillcode.scapiimporter.importers.ship.repository.ShipRepository
import dev.skillcode.scapiimporter.utils.HttpClient
import dev.skillcode.scapiimporter.utils.Importer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ShipImporter(
    private val objectMapper: ObjectMapper,
    private val shipRepository: ShipRepository,
    @Value("\${rsi.url.ships}") private val url: String
) : Importer {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun import() {
        val responseJson = HttpClient.makeRequest(url).body

        if (responseJson.isNullOrBlank())
            throw InvalidResponseException("null or empty")

        val mappedResponse = mapJsonToResponseObject(responseJson)
            ?: throw InvalidResponseException("Mapping response to json failed")

        if (mappedResponse.success != 1)
            throw UnexpectedValueException(mappedResponse.success.toString(), "1")

        val data = mappedResponse.data

        if (data.isNullOrEmpty())
            throw InvalidResponseException("data is null or empty: $data")

        data.forEach {
            val id = it["id"]
            if (!id.canConvertToInt())
                throw UnexpectedValueException("id $id is not int")

            try {
                val ship = JsonToShipConverter.convert(it)
                logger.info("Ship converted: ${ship.name}")

                val dbShip = shipRepository.findById(id.intValue())

                shipRepository.save<ShipEntity>(ship)
                logger.info("Ship ${if (dbShip.isPresent) "updated" else "imported"}: ${ship.name}")

            } catch (exception: Exception) {
                logger.error("An error occurred while importing ship: ${exception.message}", exception)
            }
        }
    }

    private fun mapJsonToResponseObject(json: String) =
        objectMapper.readValue(json, ShipApiResponse::class.java)

}
