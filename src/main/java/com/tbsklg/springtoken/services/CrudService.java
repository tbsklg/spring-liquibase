package com.tbsklg.springtoken.services;

import java.util.Optional;

public interface CrudService<T, ID> {

    void save(ID id, T object);

    Optional<T> findById(ID id);
}
