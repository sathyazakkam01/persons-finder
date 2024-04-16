package com.persons.finder.presentation

import com.persons.finder.data.Person
import com.persons.finder.domain.services.PersonsService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("api/v1/persons")
class PersonController(private val personService: PersonsService) {


    // (JSON) Body and return the id of the created entity
    @PostMapping
    fun createPerson(@RequestBody person: Person): Person = personService.save(person)


    // Example
    // John has the list of people around them, now they need to retrieve everybody's names to display in the app
    // API would be called using person or persons ids
    @GetMapping("/{ids}")
    fun getPersonsByIds(@PathVariable ids: List<Long>): List<String> {
        return personService.getPersonsByIds(ids).map { it.name }
    }


}