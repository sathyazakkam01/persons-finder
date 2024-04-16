package com.persons.finder.presentation

import com.persons.finder.data.Location
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.exception.ReferenceIntegrityException
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(LocationController::class)
class LocationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var locationService: LocationsService

    @Test
    fun update_Create_Location_Test() {
        val location = Location(1L, 40.7128, -74.0060)
        mockMvc.perform(put("/api/locations/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"latitude": 40.7128, "longitude": -74.0060}"""))
                .andExpect(status().isOk)

        verify(locationService).addLocation(location)
    }

    @Test
    fun remove_Location_Test() {
        mockMvc.perform(delete("/api/locations/{referenceId}", 1L))
                .andExpect(status().isOk)

        verify(locationService).removeLocation(1L)
    }

    @Test
    fun find_Nearby_Test() {
        // Mocking the service to return a list of Location objects
        val locations = listOf(
                Location(1L, 40.7128, -74.0060),
                Location(2L, 40.7138, -74.0070)
        )
        given(locationService.findAround(40.7128, -74.0060, 10.0)).willReturn(locations)

        // Perform the GET request and verify the results
        mockMvc.perform(get("/api/locations")
                .param("latitude", "40.7128")
                .param("longitude", "-74.0060")
                .param("distance", "10.0"))
                .andExpect(status().isOk)
                .andExpect(content().json("""
            [
                {"referenceId":1,"latitude":40.7128,"longitude":-74.0060},
                {"referenceId":2,"latitude":40.7138,"longitude":-74.0070}
            ]
        """))

        verify(locationService).findAround(40.7128, -74.0060, 10.0)
    }

}
