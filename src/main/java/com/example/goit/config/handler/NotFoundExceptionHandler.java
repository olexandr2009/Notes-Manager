package com.example.goit.config.handler;

import com.example.goit.crudservice.ObjectNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NotFoundExceptionHandler{
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(ObjectNotFoundException objectNotFoundException){
        ModelAndView modelAndView = new ModelAndView("notFound");
        modelAndView.addObject("message", objectNotFoundException.getMessage());
        return modelAndView;
    }
}
