package com.example.goit.note;

import com.example.goit.crudservice.CrudService;
import com.example.goit.crudservice.ObjectNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class InMemoryNoteCrudService implements CrudService<Note> {
    List<Note> DATA;
    public InMemoryNoteCrudService(){
        this(new ArrayList<>());
    }
    public InMemoryNoteCrudService(List<Note> data){
        DATA = new ArrayList<>(data);
    }
    @Override
    public List<Note> listAll() {
        return new ArrayList<>(DATA);
    }
    @Override
    public Note add(Note note) {
        DATA.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) throws ObjectNotFoundException {
        try {
            DATA.remove(getById(id));
        } catch (Exception ex){
            throw new ObjectNotFoundException("DeleteById Exception");
        }
    }

    @Override
    public void update(Note note) throws ObjectNotFoundException {
        try {
            Note toUpdate = getById(note.getId());
            toUpdate.setTitle(note.getTitle());
            toUpdate.setContent(note.getContent());
        } catch (Exception ex){
            throw new ObjectNotFoundException("Update Exception");
        }
    }

    @Override
    public Note getById(long id) throws ObjectNotFoundException {
        Optional<Note> toFind = DATA.stream().filter(note -> note.getId() == id).findFirst();
        if (toFind.isPresent()){
            return toFind.get();
        }
        throw new ObjectNotFoundException("GetById Exception");
    }
}
