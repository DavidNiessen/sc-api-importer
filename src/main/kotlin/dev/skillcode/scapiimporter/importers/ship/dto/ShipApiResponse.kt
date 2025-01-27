package dev.skillcode.scapiimporter.importers.ship.dto

import com.fasterxml.jackson.databind.JsonNode

data class ShipApiResponse(
    val success: Int? = null,
    val code: String? = null,
    val msg: String? = null,
    val data: List<JsonNode>? = null,
)
