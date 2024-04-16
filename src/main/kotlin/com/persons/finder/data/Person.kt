package com.persons.finder.data

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "person", indexes = [Index(name = "idx_name", columnList = "name")])
data class Person(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String = ""

)
