package com.example.qars.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GetAllAdvice {
    @ResponseBody
    @ExceptionHandler(GetAllException.class)
    String getAllHandler(GetAllException ex) {
        return ex.getMessage();
    }
}

