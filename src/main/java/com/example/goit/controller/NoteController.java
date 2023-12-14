package com.example.goit.controller;

import com.example.goit.note.Note;
import com.example.goit.note.NoteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RequestMapping("/notes")
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteController {
    NoteService noteService;

    @GetMapping("/list")
    public ModelAndView findList(){
        ModelAndView modelAndView = new ModelAndView("noteList");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }
    @GetMapping("/editForm")
    public ModelAndView editForm(@RequestParam Long id){
        noteService.contains(id);
        ModelAndView modelAndView = new ModelAndView("editForm");
        modelAndView.addObject("id", id);
        return modelAndView;
    }
    @GetMapping("/addForm")
    public ModelAndView addForm(){
        return new ModelAndView("addForm");
    }

    @PostMapping("/edit")
    public RedirectView editNoteRedirect(Note note){
        noteService.addOrUpdate(note);
        return new RedirectView("/notes/list");
    }
    @PostMapping("/delete/{id}")
    public RedirectView deleteByIdRedirect(@PathVariable Long id){
        noteService.deleteById(id);
        return new RedirectView("/notes/list");
    }
}
