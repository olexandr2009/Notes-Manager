package com.example.goit.controller;

import com.example.goit.note.Note;
import com.example.goit.note.NoteCrudService;
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
    NoteCrudService noteCrudService;

    @GetMapping("/list")
    public ModelAndView findList(){
        ModelAndView modelAndView = new ModelAndView("noteList");
        modelAndView.addObject("notes", noteCrudService.listAll());
        return modelAndView;
    }
    @GetMapping("/editForm/{id}")
    public ModelAndView editForm(@PathVariable String id){
        noteCrudService.contains(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView("editForm");
        modelAndView.addObject("id", id);
        return modelAndView;
    }
    @GetMapping("/addForm")
    public ModelAndView addForm(){
        return new ModelAndView("addForm");
    }

    @PostMapping("/edit")
    public RedirectView editNoteRedirect(
            @RequestParam String id,
            @RequestParam String title,
            @RequestParam String content
    ){
        Note note = new Note(Long.parseLong(id), title, content);
        noteCrudService.addOrUpdate(note);
        return new RedirectView("/notes/list");
    }
    @PostMapping("/delete/{id}")
    public RedirectView deleteByIdRedirect(@PathVariable Long id){
        noteCrudService.deleteById(id);
        return new RedirectView("/notes/list");
    }
}
