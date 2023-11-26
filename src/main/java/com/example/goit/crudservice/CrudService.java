package com.example.goit.crudservice;

import com.example.goit.note.Note;

import java.util.List;

public interface CrudService<T> {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(long id) throws ObjectNotFoundException;
    void update(Note note) throws ObjectNotFoundException;
    Note getById(long id) throws ObjectNotFoundException;
}
