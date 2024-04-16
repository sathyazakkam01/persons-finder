package com.persons.finder.data

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.Column

@Entity
@Table(name = "location", indexes = [Index(name = "idx_reference_id", columnList = "referenceId")])
data class Location(

        // Tip: Person's id can be used for this field
        @Id
        var referenceId: Long = 0,
        @Column(nullable = false)
        var latitude: Double = 0.0,
        @Column(nullable = false)
        var longitude: Double = 0.0

)
