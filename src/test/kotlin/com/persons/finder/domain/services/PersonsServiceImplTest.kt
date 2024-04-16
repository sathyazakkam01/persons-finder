package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.repository.PersonRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class PersonsServiceImplTest {

    @Mock
    lateinit var personRepository: PersonRepository

    @InjectMocks
    lateinit var personsService: PersonsServiceImpl

    @Test
    fun getById_Test() {
        val expectedPerson = Person(1L, "John Doe")
        whenever(personRepository.findById(1L)).thenReturn(Optional.of(expectedPerson))

        val actualPerson = personsService.getById(1L)

        assertEquals(expectedPerson, actualPerson)
    }

    @Test
    fun getById_NotFound_test() {

        whenever(personRepository.findById(1L)).thenReturn(Optional.empty())

        val exception = assertFailsWith<RuntimeException> {
            personsService.getById(1L)
        }
        assertEquals("Person not found with ID: 1", exception.message)
    }

    @Test
    fun savePerson_Test() {
        val personToSave = Person(1L, "Jane Doe")
        whenever(personRepository.save(eq(personToSave))).thenReturn(personToSave)

        val savedPerson = personsService.save(personToSave)

        assertEquals(personToSave, savedPerson)
        verify(personRepository).save(eq(personToSave))
    }

    @Test
    fun getPersonsByIds_Test() {
        val ids = listOf(1L, 2L)
        val expectedPersons = listOf(Person(1L, "John Doe"), Person(2L, "Jane Doe"))
        whenever(personRepository.findAllById(ids)).thenReturn(expectedPersons)

        val actualPersons = personsService.getPersonsByIds(ids)

        assertEquals(expectedPersons, actualPersons)
    }
}
