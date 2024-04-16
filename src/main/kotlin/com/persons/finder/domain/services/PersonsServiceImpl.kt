package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl(private val personRepository: PersonRepository) : PersonsService {

    override fun getById(id: Long): Person {
        return personRepository.findById(id).orElseThrow {
            RuntimeException("Person not found with ID: $id")
        }
    }

    override fun save(person: Person): Person {
        return personRepository.save(person)
    }

    override fun getPersonsByIds(ids: List<Long>): MutableList<Person> {
        return personRepository.findAllById(ids)
    }

}