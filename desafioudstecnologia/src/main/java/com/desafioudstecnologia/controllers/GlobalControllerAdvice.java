package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.exeptions.DuplicateRecordException;
import com.desafioudstecnologia.exeptions.InvalidArgumentException;
import com.desafioudstecnologia.exeptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateRecordException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleIllegalArgumentException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(InvalidArgumentException.returnMessage(exception));
    }

}
