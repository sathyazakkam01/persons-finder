package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.exception.ReferenceIntegrityException
import com.persons.finder.repository.LocationRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class LocationsServiceImpl(private val locationRepository: LocationRepository) : LocationsService {
    override fun addLocation(location: Location) {
        try {
            val existingLocation = locationRepository.findById(location.referenceId)
            if (existingLocation.isPresent) {
                val updatedLocation = existingLocation.get()
                updatedLocation.latitude = location.latitude
                updatedLocation.longitude = location.longitude
                locationRepository.save(updatedLocation)
            } else {
                locationRepository.save(location)
            }
        }
        catch(ex: DataIntegrityViolationException){
            throw ReferenceIntegrityException("No corresponding person found for reference ID: ${location.referenceId}")
        }
    }

    override fun removeLocation(locationReferenceId: Long) {
        locationRepository.deleteById(locationReferenceId)
    }

    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double): List<Location> {
        return locationRepository.findNearbyPeople(latitude, longitude, radiusInKm)
    }

}