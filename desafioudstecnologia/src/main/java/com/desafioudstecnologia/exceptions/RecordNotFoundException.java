package com.desafioudstecnologia.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String param) {
        super("Registro não encontrado com o parâmetro: " + param);
    }

}
