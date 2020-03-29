package com.tbsklg.springtoken.bootstrap;

import com.tbsklg.springtoken.model.Person;
import com.tbsklg.springtoken.model.PersonId;
import com.tbsklg.springtoken.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = {"dev", "local"})
public class BootstrapData implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapData.class);

    private final PersonRepository personRepository;

    public BootstrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.error("Starting database preparation...");

        this.personRepository.deleteAll();

        int numberOfPersonsToCreate = 50_000;

        for (int i = 1; i <= numberOfPersonsToCreate; i++) {
            Person person = Person.from(PersonId.of(String.valueOf(i)),
                    "simple_username",
                    "simple_password"
            );
            this.personRepository.save(person);
        }
    }
}
