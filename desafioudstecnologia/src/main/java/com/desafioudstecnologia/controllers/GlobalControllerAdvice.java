package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.exceptions.DuplicateRecordException;
import com.desafioudstecnologia.exceptions.InvalidArgumentException;
import com.desafioudstecnologia.exceptions.InvalidDateArgumentException;
import com.desafioudstecnologia.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateRecordException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleIllegalArgumentException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidArgumentException.returnMessage(exception));
    }

    @ExceptionHandler(InvalidDateArgumentException.class)
    public ResponseEntity<String> handleInvalidDateArgumentException(InvalidDateArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
