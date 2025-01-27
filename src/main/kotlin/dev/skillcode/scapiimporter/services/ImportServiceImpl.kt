package dev.skillcode.scapiimporter.services

import dev.skillcode.scapiimporter.importers.ship.ShipImporter
import org.springframework.stereotype.Service

@Service
class ImportServiceImpl(
    private val shipImporter: ShipImporter
) : ImportService {

    override fun importAll() {
        shipImporter.import()
        
    }
}
