package dev.skillcode.scapiimporter.importers.ship.converter

import com.fasterxml.jackson.databind.ObjectMapper
import dev.skillcode.scapiimporter.exceptions.JsonException
import dev.skillcode.scapiimporter.importers.ship.entity.Crew
import dev.skillcode.scapiimporter.importers.ship.entity.Manufacturer
import dev.skillcode.scapiimporter.importers.ship.entity.ShipEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [JsonToShipConverter::class])
@SpringBootTest
class JsonToShipConverterTest {

    val shipJson = ClassPathResource("ships/ship_polaris.json")
    val shipJsonInvalid = ClassPathResource("ships/ship_polaris_invalid.json")

    @Test
    fun convertsCorrectly() {
        val jsonContent = shipJson.file.readText(Charsets.UTF_8)
        val entity = JsonToShipConverter.convert(ObjectMapper().readTree(jsonContent))
        val expected = ShipEntity(
            id = 116,
            name = "Polaris",
            description = "Nimble corvette-class capital ship packing a powerful punch with a full armament of turrets and torpedoes, small craft hangar, and habitation for a crew of 12.",
            type = "combat",
            focus = "Corvette",
            productionNote = null,
            productionStatus = "flight-ready",
            size = "capital",
            height = 35.0,
            length = 166.0,
            mass = 1.7155E7,
            beam = 82.0,
            afterburnerSpeed = 940.0,
            scmSpeed = 145.0,
            lastModified = "2 months ago",
            url = "/pledge/ships/polaris/Polaris",
            crew = Crew(min = 6, max = 12),
            manufacturer = Manufacturer(
                name = "Roberts Space Industries",
                code = "RSI",
                description = "The original creators of the engine that kickstarted humanity’s expansion into space, Roberts Space Industries build a wide range of spaceships that serve all needs starting at basic interstellar travel to deep exploration on the outer edges of the galaxy. The tagline is “Roberts Space Industries: Delivering the Stars since 2075”"
            )
        )

        assertNotNull(entity)
        assertEquals(entity, expected)
    }

    @Test
    fun failsIfPropertyNotFound() {
        // json file is missing afterburner_speed property
        val jsonContent = shipJsonInvalid.file.readText(Charsets.UTF_8)
        assertThrows<JsonException> { JsonToShipConverter.convert(ObjectMapper().readTree(jsonContent)) }
    }

}
