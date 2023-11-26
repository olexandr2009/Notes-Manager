package com.example.goit.crudservice;

import com.example.goit.note.Note;

import java.util.List;

public interface CrudService<T> {
    List<T> listAll();
    T add(T t);
    void deleteById(long id) throws ObjectNotFoundException;
    void update(T note) throws ObjectNotFoundException;
    T getById(long id) throws ObjectNotFoundException;
}
