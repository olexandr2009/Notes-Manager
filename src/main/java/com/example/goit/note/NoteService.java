package com.example.goit.note;

import com.example.goit.crudservice.CrudService;
import com.example.goit.crudservice.ObjectNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteService implements CrudService<Note> {

    NoteRepository noteRepository;
    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> addAll(List<Note> list){
        return noteRepository.saveAll(list);
    }

    @Override
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) throws ObjectNotFoundException {
        noteRepository.deleteById(id);
    }

    @Override
    public Note addOrUpdate(Note note) {
        if (contains(note.getId())){
            return update(note);
        }
        return add(note);
    }

    @Override
    public Note update(Note note) throws ObjectNotFoundException {
        Note toUpdate = getById(note.getId());
        toUpdate.setTitle(note.getTitle());
        toUpdate.setContent(note.getContent());
        return noteRepository.save(note);
    }

    @Override
    public Note getById(long id) throws ObjectNotFoundException {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Note with id %s not found".formatted(id)));
    }

    @Override
    public boolean contains(long id) {
        return noteRepository.findById(id).isPresent();
    }
}
