package com.tbsklg.springtoken.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PersonControllerIT extends Specification {

    @Autowired
    WebTestClient webTestClient

    @Unroll
    def "PersonAPI is called with personId {#personId}"() {
        when: "create person"
        def response = webTestClient.post().uri("/api/v1/persons/" + personId + "/finapi").exchange()

        then: "person is created or already exists"
        response.expectStatus().isEqualTo(expectedStatus)

        where:
        personId | expectedStatus
        1234     | HttpStatus.CREATED
        1234     | HttpStatus.CONFLICT
        1234     | HttpStatus.CONFLICT
        2345     | HttpStatus.CREATED
    }

    @Unroll
    def "PersonAPI is called with personId to getAccessToken"() {
        given: "person exists {#personExists}"
        if (personShouldExist) {
            webTestClient.post().uri("/api/v1/persons/" + personId + "/finapi").exchange()
        }

        when: "get accesstoken"
        def response = webTestClient.get().uri("/api/v1/persons/" + personId + "/finapi/accesstoken").exchange()

        then: ""
        response.expectStatus().isEqualTo(expectedStatus)

        where:
        personShouldExist | personId | expectedStatus
        true              | "1234"   | HttpStatus.OK
        false             | "5668"   | HttpStatus.NOT_FOUND
    }
}
