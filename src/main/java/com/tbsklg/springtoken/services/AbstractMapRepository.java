package com.tbsklg.springtoken.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractMapRepository<T, ID> implements CrudService<T, ID>{

    protected Map<ID, T> map = new HashMap();


    @Override
    public void save(ID id, T object) {
        map.put(id, object);
    }

    @Override
    public Optional<T> findById(ID id) {
       return Optional.ofNullable(map.get(id));
    }
}
