package com.persons.finder.repository

import com.persons.finder.data.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LocationRepository : JpaRepository<Location, Long> {
    @Query("SELECT l FROM Location l WHERE acos(sin(radians(:lat)) * sin(radians(l.latitude)) + cos(radians(:lat)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:lon))) * 6371 < :distance")
    fun findNearbyPeople(@Param("lat") lat: Double, @Param("lon") lon: Double, @Param("distance") distance: Double): List<Location>
}