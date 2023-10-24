package com.desafioudstecnologia.exceptions;

public class InvalidDateArgumentException extends RuntimeException {

    public InvalidDateArgumentException(String date) {
        super("Data " + date + " invalida. Utilize o formato dd/MM/yyyy.");
    }

}
