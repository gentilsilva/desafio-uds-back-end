package com.desafioudstecnologia.exeptions;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class InvalidArgumentException {

    public static String returnMessage(MethodArgumentNotValidException exception) {
        String field = exception.getFieldError().getField();
        String message = exception.getFieldError().getDefaultMessage();
        return "O(a) " + field + " " + message;
    }

}
