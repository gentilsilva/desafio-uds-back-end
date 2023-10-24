package com.desafioudstecnologia.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class InvalidArgumentException extends RuntimeException{

    public static String returnMessage(MethodArgumentNotValidException exception) {
        String field = exception.getFieldError().getField();
        String message = exception.getFieldError().getDefaultMessage();
        return "O(a) " + field + " " + message;
    }

}
