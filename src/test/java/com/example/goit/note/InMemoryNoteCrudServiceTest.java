package com.example.goit.note;

import com.example.goit.crudservice.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryNoteCrudServiceTest {
    private InMemoryNoteCrudService inMemoryNoteCrudService;
    @BeforeEach
    void init(){
        inMemoryNoteCrudService = new InMemoryNoteCrudService(initDATA());
        System.out.println(inMemoryNoteCrudService.listAll());
    }
    List<Note> initDATA(){
        ArrayList<Note> notes = new ArrayList<>();
        for (long id = 1; id <= 10; id++) {
            notes.add(Note.builder()
                    .id(id)
                    .title("Title%d".formatted(id))
                    .content("Content%d".formatted(id))
                    .build());
        }
        return notes;
    }

    @Test
    void testListAllWorksCorrectly() {
        assertEquals(initDATA(),  inMemoryNoteCrudService.listAll());
    }

    @Test
    void testDeleteByIdThrowsEx() {
        assertThrows(ObjectNotFoundException.class,() -> inMemoryNoteCrudService.deleteById(946586784L));
    }

    @Test
    void testUpdateWorksCorrectly() {
        Note note = new Note(1L, "updatedTitle", "updatedContent");
        try {
            inMemoryNoteCrudService.update(note);
            assertEquals(note,inMemoryNoteCrudService.getById(1L));
        } catch (ObjectNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    void testGetByIdWorksCorrectly() {
        try {
            Note note = new Note(1L, "Title1", "Content1");
            assertEquals(note, inMemoryNoteCrudService.getById(1L));
        } catch (ObjectNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}