package com.example.goit.note;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class NoteServiceTest {
    @Autowired
    NoteService noteService;
    @Autowired
    NoteRepository noteRepository;

    @AfterEach
    void drop(){
        noteRepository.deleteAll();
    }
    Note getNote(long id){
        Note note = new Note();
        note.setId(id);
        note.setTitle("Title" + id);
        note.setContent("Content" + id);
        return note;
    }
    Note saveNote(long id){
        return noteRepository.saveAndFlush(getNote(id));
    }
    @Test
    void testGetById() {
        long id = 1L;
        Note expected = saveNote(id);

        Note actual = noteService.getById(id);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    void testAdd(){
        long id = 2L;
        Note expected = getNote(id);

        Note saved = noteService.add(expected);

        assertNotNull(saved);
        assertEquals(expected.toString(), saved.toString());
    }

    @Test
    void testUpdate(){
        long id = 3L;
        Note note = saveNote(id);

        Note updated = new Note();
        updated.setId(id);
        updated.setTitle("Title");
        updated.setContent("Content");
        System.out.println(noteRepository.findAll());
        Note actual = noteService.update(updated);

        assertNotNull(actual);
        assertNotEquals(note.toString(), actual.toString());
        assertEquals(updated.toString(), actual.toString());
    }
    @Test
    void testDeleteById(){
        long id = 4L;
        saveNote(id);

        noteService.deleteById(id);

        assertFalse(noteRepository.findById(id).isPresent());
    }


}