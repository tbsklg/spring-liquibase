package com.tbsklg.springtoken.services

import com.tbsklg.springtoken.model.FinApiAccessToken
import com.tbsklg.springtoken.model.Person
import com.tbsklg.springtoken.repository.PersonRepository
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class PersonServiceTest extends Specification {

    PersonRepository personRepository = Mock(PersonRepository)
    FinAPIClient finAPI = Mock(FinAPIClient)

    @Unroll
    def "Calls first Repo and than FinAPI to get person"() {
        PersonService personService = new PersonService(personRepository, finAPI)

        when:
        def responseEntity = personService.createPerson(personId)

        then:
        responseEntity.getStatusCode() == httpStatus

        callsToRepo * personRepository.findByPersonId(personId) >> personRepo
        callsToFinApi * finAPI.createPerson(personId) >> Person.from(personId, "213131", "avcdefdsaf")

        where:
        personId | personRepo                                         | callsToFinApi | callsToRepo | httpStatus
        "1234"   | Optional.of(Person.from(personId, "sfds", "fdsa")) | 0             | 1           | HttpStatus.CONFLICT
        "5678"   | Optional.empty()                                   | 1             | 1           | HttpStatus.CREATED
    }

    def "Calls first Repo and than FinApi to get accesstoken"() {
        PersonService personService = new PersonService(personRepository, finAPI)

        when:
        def responseEntity = personService.getAccessToken(personId)

        then:
        responseEntity.getStatusCode() == httpStatus

        callsToRepo * personRepository.findByPersonId(personId) >> personRepo
        if (personRepo.isPresent()) {
            callsToFinApi * finAPI.getAccessToken(personRepo.get()) >> new FinApiAccessToken("testToken")
        }

        where:
        personId | personRepo                                            | callsToFinApi | callsToRepo | httpStatus
        "1234"   | Optional.of(Person.from(personId, "2123", "1232123")) | 1             | 1           | HttpStatus.OK
        "456"    | Optional.empty()                                      | 0             | 1           | HttpStatus.NOT_FOUND
    }
}