package com.persons.finder.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.persons.finder.data.Person
import com.persons.finder.domain.services.PersonsService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.mockito.kotlin.whenever

@ExtendWith(SpringExtension::class)
@WebMvcTest(PersonController::class)
class PersonControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper by lazy {
        ObjectMapper().registerKotlinModule()
    }

    @MockBean
    private lateinit var personService: PersonsService


    @Test
    fun createPerson_Test() {
        val person = Person(1L, "John Doe")
        whenever(personService.save(person)).thenReturn(person)

        mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(person)))
    }

    @Test
    fun getPersonsByIds_Test() {
        val ids = listOf(1L, 2L)
        val persons = listOf(Person(1L, "John Doe"), Person(2L, "Jane Doe"))
        whenever(personService.getPersonsByIds(ids)).thenReturn(persons)

        mockMvc.perform(get("/api/v1/persons/{ids}", ids.joinToString(","))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(listOf("John Doe", "Jane Doe"))))
    }
}
