package dev.skillcode.scapiimporter.importers.ship.entity

import jakarta.persistence.*

@Entity
@Table(name = "ships")
data class ShipEntity(
    @Id @Column(name = "id") val id: Int = 0,
    @Column(name = "name", length = 250) val name: String? = null,
    @Column(name = "description", length = 2500) val description: String? = null,
    @Column(name = "type", length = 250) val type: String? = null,
    @Column(name = "focus", length = 250) val focus: String? = null,
    @Column(name = "production_note", length = 2500) val productionNote: String? = null,
    @Column(name = "production_status", length = 2500) val productionStatus: String? = null,
    @Column(name = "size", length = 250) val size: String? = null,
    @Column(name = "height") val height: Double? = null,
    @Column(name = "length") val length: Double? = null,
    @Column(name = "mass") val mass: Double? = null,
    @Column(name = "beam") val beam: Double? = null,
    @Column(name = "afterburner_speed") val afterburnerSpeed: Double? = null,
    @Column(name = "scm_speed") val scmSpeed: Double? = null,
    @Column(name = "last_modified", length = 250) val lastModified: String? = null,
    @Column(name = "url", length = 250) val url: String? = null,
    val crew: Crew = Crew(),
    val manufacturer: Manufacturer = Manufacturer()
)

@Embeddable
data class Manufacturer(
    @Column(name = "manufacturer_name", length = 250)
    val name: String? = null,
    @Column(name = "manufacturer_code", length = 250)
    val code: String? = null,
    @Column(name = "manufacturer_description", length = 2500)
    val description: String? = null,
)

@Embeddable
data class Crew(
    @Column(name = "crew_min")
    val min: Int? = null,
    @Column(name = "crew_max")
    val max: Int? = null,
)
