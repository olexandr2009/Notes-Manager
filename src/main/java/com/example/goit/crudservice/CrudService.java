package com.example.goit.crudservice;

import java.util.List;

public interface CrudService<T> {
    List<T> listAll();
    T addOrUpdate(T t);
    void deleteById(long id) throws ObjectNotFoundException;
    void update(T t) throws ObjectNotFoundException;
    T getById(long id) throws ObjectNotFoundException;

    boolean contains(long id);
}
