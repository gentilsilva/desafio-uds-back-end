package com.desafioudstecnologia.exeptions;

import java.time.LocalDate;

public class InvalidDateArgumentException extends RuntimeException {

    public InvalidDateArgumentException(String date) {
        super("Data " + date + " invalida. Utilize o formato dd/MM/yyyy.");
    }

}
