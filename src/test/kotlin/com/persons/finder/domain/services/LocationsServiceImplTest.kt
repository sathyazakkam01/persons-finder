package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.exception.ReferenceIntegrityException
import com.persons.finder.repository.LocationRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.dao.DataIntegrityViolationException
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class LocationsServiceImplTest {
    @Mock
    private val locationRepository: LocationRepository? = null

    @InjectMocks
    private val service: LocationsServiceImpl? = null

    @Test
    fun addLocation_ExistingLocation_Test() {
        val existingLocation = Location(1L, 40.7128, -74.0060)
        val newLocation = Location(1L, 40.7138, -74.0070)
        Mockito.`when`(locationRepository?.findById(1L)).thenReturn(Optional.of(existingLocation))

        service!!.addLocation(newLocation)

        Mockito.verify(locationRepository)?.save(existingLocation)
        Assertions.assertEquals(40.7138, existingLocation.latitude)
        Assertions.assertEquals(-74.0070, existingLocation.longitude)
    }

    @Test
    fun addLocation_NewLocation_Test() {
        val newLocation = Location(2L, 40.7148, -74.0080)
        Mockito.`when`(locationRepository?.findById(2L)).thenReturn(Optional.empty())

        service!!.addLocation(newLocation)

        Mockito.verify(locationRepository)?.save(newLocation)
    }

    @Test
    fun addLocation_Failure_Test() {
        val newLocation = Location(3L, 40.7158, -74.0090)
        Mockito.`when`(locationRepository?.findById(3L)).thenReturn(Optional.empty())
        Mockito.doThrow(DataIntegrityViolationException("Integrity Constraint Violation")).`when`(locationRepository)?.save(newLocation)

        val exception: Exception = Assertions.assertThrows(ReferenceIntegrityException::class.java) { service!!.addLocation(newLocation) }
        Assertions.assertEquals("No corresponding person found for reference ID: 3", exception.message)
    }
}
