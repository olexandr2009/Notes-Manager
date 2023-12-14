package com.example.goit.crudservice;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message){
        super(message);
    }
}
