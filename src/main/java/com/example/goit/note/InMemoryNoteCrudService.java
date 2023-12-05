package com.example.goit.note;

import com.example.goit.crudservice.ObjectNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InMemoryNoteCrudService implements NoteCrudService {
    List<Note> DATA;

    public InMemoryNoteCrudService() {
        DATA = new ArrayList<>();
    }

    public InMemoryNoteCrudService(List<Note> data) {
        DATA = new ArrayList<>(data);
    }

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(DATA);
    }

    @Override
    public Note addOrUpdate(Note noteToAdd) {
        boolean exists = DATA.stream().anyMatch(note -> note.getId() == noteToAdd.getId());
        if (exists) {
            update(noteToAdd);
        } else {
            DATA.add(noteToAdd);
        }
        return noteToAdd;
    }

    @Override
    public void deleteById(long id) throws ObjectNotFoundException {
        DATA.remove(getById(id));
    }

    @Override
    public void update(Note note) throws ObjectNotFoundException {
        if (contains(note.getId())) {
            int index = DATA.indexOf(getById(note.getId()));
            DATA.set(index, note);
            return;
        }
        throw new ObjectNotFoundException("Update Exception");
    }

    @Override
    public Note getById(long id) throws ObjectNotFoundException {
        if (!contains(id)){
            throw new ObjectNotFoundException("GetById Exception");
        }
        return DATA.stream().filter(note -> note.getId() == id).findFirst().get();
    }

    @Override
    public boolean contains(long id) {
        return DATA.stream().anyMatch(note -> note.getId() == id);
    }
}
