package dev.skillcode.scapiimporter.extensions

import com.fasterxml.jackson.databind.JsonNode
import dev.skillcode.scapiimporter.exceptions.JsonException


public fun JsonNode.asIntOrNull(): Int? = if (this.isNull) null else this.asInt()
public fun JsonNode.asDoubleOrNull(): Double? = if (this.isNull) null else this.asDouble()
public fun JsonNode.asBooleanOrNull(): Boolean? = if (this.isNull) null else this.asBoolean()
public fun JsonNode.asLongOrNull(): Long? = if (this.isNull) null else this.asLong()
public fun JsonNode.asStringOrNull(): String? = if (this.isNull) null else this.asText()

public fun JsonNode.getPropertyIfExists(
    propertyName: String
): JsonNode = this[propertyName] ?: throw JsonException("Property '$propertyName' is missing")
