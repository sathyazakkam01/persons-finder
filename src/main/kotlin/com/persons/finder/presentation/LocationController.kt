    package com.persons.finder.presentation

    import com.persons.finder.data.Location
    import com.persons.finder.domain.services.LocationsService
    import com.persons.finder.exception.ReferenceIntegrityException
    import org.springframework.http.HttpStatus
    import org.springframework.http.ResponseEntity
    import org.springframework.web.bind.annotation.RestController
    import org.springframework.web.bind.annotation.RequestMapping
    import org.springframework.web.bind.annotation.PutMapping
    import org.springframework.web.bind.annotation.PathVariable
    import org.springframework.web.bind.annotation.RequestBody
    import org.springframework.web.bind.annotation.DeleteMapping
    import org.springframework.web.bind.annotation.GetMapping
    import org.springframework.web.bind.annotation.RequestParam
    import org.springframework.web.bind.annotation.ExceptionHandler


    @RestController
    @RequestMapping("/api/locations")
    class LocationController(private val locationService: LocationsService) {


        // API to update/create someone's location using latitude and longitude
        @PutMapping("/{id}")
        fun updateOrCreateLocation(@PathVariable id: Long, @RequestBody location: Location) {
            location.referenceId = id // Ensure the ID from the path is used
            locationService.addLocation(location)
        }

        @DeleteMapping("/{referenceId}")
        fun removeLocation(@PathVariable referenceId: Long) {
            locationService.removeLocation(referenceId)
        }


        //GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        //API just return a list of persons ids (JSON)
        @GetMapping
        fun findNearby(@RequestParam latitude: Double, @RequestParam longitude: Double, @RequestParam distance: Double) =
                locationService.findAround(latitude, longitude, distance)

        @ExceptionHandler(ReferenceIntegrityException::class)
        fun handleReferenceIntegrityException(ex: ReferenceIntegrityException): ResponseEntity<String> {
            return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
    }