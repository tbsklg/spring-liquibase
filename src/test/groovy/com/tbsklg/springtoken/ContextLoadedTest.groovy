package com.tbsklg.springtoken

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import spock.lang.Specification

@SpringBootTest
class ContextLoadedTest extends Specification {

    @Autowired
    Environment environment

    def "Environment is loaded"() {
        expect: "environment is present"
        environment != null
    }
}
