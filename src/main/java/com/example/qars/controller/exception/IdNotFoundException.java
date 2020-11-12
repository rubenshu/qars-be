package com.example.qars.controller.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Integer id){
        super("Item with id "+id+" is currently not available.");
    }
}
