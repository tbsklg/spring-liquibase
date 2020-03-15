package com.tbsklg.springtoken.repository;

import com.tbsklg.springtoken.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("Select t from Person t where t.personId = :personId")
    Optional<Person> findByPersonId(@Param("personId") String personId);
}
