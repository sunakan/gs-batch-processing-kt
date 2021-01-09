package com.example.gsbatchprocessingkt

import com.example.gsbatchprocessingkt.entity.Person
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import java.lang.Exception
import kotlin.Throws

class PersonItemProcessor : ItemProcessor<Person, Person> {
    @Throws(Exception::class)
    override fun process(person: Person): Person {
        val firstName = person.firstName.toUpperCase()
        val lastName = person.lastName.toUpperCase()
        val transformedPerson = Person(firstName, lastName)
        log.info("Converting ($person) into ($transformedPerson)")
        return transformedPerson
    }

    companion object {
        private val log = LoggerFactory.getLogger(PersonItemProcessor::class.java)
    }
}
