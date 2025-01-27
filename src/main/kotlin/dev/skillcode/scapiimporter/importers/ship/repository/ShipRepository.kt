package dev.skillcode.scapiimporter.importers.ship.repository

import dev.skillcode.scapiimporter.importers.ship.entity.ShipEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShipRepository : JpaRepository<ShipEntity, Int> {
    fun findById(id: Int?): ShipEntity?
}
