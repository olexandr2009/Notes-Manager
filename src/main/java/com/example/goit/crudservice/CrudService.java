package com.example.goit.crudservice;

import com.example.goit.note.Note;

import java.util.List;

public interface CrudService<T> {
    List<T> listAll();
    List<Note> addAll(List<Note> list);
    T add(T t);
    void deleteById(long id) throws ObjectNotFoundException;
    T addOrUpdate(Note note);
    T update(T t) throws ObjectNotFoundException;
    T getById(long id) throws ObjectNotFoundException;
    boolean contains(long id) throws ObjectNotFoundException;
}
