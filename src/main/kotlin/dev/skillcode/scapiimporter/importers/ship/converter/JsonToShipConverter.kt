package dev.skillcode.scapiimporter.importers.ship.converter

import com.fasterxml.jackson.databind.JsonNode
import dev.skillcode.scapiimporter.exceptions.JsonException
import dev.skillcode.scapiimporter.extensions.asDoubleOrNull
import dev.skillcode.scapiimporter.extensions.asIntOrNull
import dev.skillcode.scapiimporter.extensions.asStringOrNull
import dev.skillcode.scapiimporter.extensions.getPropertyIfExists
import dev.skillcode.scapiimporter.importers.ship.entity.Crew
import dev.skillcode.scapiimporter.importers.ship.entity.Manufacturer
import dev.skillcode.scapiimporter.importers.ship.entity.ShipEntity
import org.springframework.core.convert.converter.Converter

object JsonToShipConverter : Converter<JsonNode, ShipEntity> {

    @Throws(JsonException::class)
    override fun convert(node: JsonNode): ShipEntity =
        ShipEntity(
            id = node.getPropertyIfExists("id").asInt(),
            name = node.getPropertyIfExists("name").asStringOrNull(),
            description = node.getPropertyIfExists("description").asStringOrNull(),
            type = node.getPropertyIfExists("type").asStringOrNull(),
            focus = node.getPropertyIfExists("focus").asStringOrNull(),
            productionNote = node.getPropertyIfExists("production_note").asStringOrNull(),
            productionStatus = node.getPropertyIfExists("production_status").asStringOrNull(),
            size = node.getPropertyIfExists("size").asStringOrNull(),
            height = node.getPropertyIfExists("height").asDoubleOrNull(),
            length = node.getPropertyIfExists("length").asDoubleOrNull(),
            mass = node.getPropertyIfExists("mass").asDoubleOrNull(),
            beam = node.getPropertyIfExists("beam").asDoubleOrNull(),
            afterburnerSpeed = node.getPropertyIfExists("afterburner_speed").asDoubleOrNull(),
            scmSpeed = node.getPropertyIfExists("scm_speed").asDoubleOrNull(),
            lastModified = node.getPropertyIfExists("time_modified").asStringOrNull(),
            url = node.getPropertyIfExists("url").asStringOrNull(),
            crew = Crew(
                min = node.getPropertyIfExists("min_crew").asIntOrNull(),
                max = node.getPropertyIfExists("max_crew").asIntOrNull(),
            ),
            manufacturer = Manufacturer(
                name = node.getPropertyIfExists("manufacturer").getPropertyIfExists("name").asStringOrNull(),
                code = node.getPropertyIfExists("manufacturer").getPropertyIfExists("code").asStringOrNull(),
                description = node.getPropertyIfExists("manufacturer").getPropertyIfExists("description")
                    .asStringOrNull(),
            )
        )

}
