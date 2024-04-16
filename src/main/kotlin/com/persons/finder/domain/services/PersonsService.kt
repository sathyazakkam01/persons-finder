package com.persons.finder.domain.services

import com.persons.finder.data.Person

interface PersonsService {
    fun getPersonsByIds(ids: List<Long>): List<Person>
    fun save(person: Person): Person
    fun getById(id: Long): Person
}