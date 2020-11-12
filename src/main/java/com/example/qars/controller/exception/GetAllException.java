package com.example.qars.controller.exception;

public class GetAllException extends RuntimeException {
        public GetAllException(){
            super("There are no items in the list to deliver");
        }
    }

