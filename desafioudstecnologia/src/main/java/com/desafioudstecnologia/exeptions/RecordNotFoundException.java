package com.desafioudstecnologia.exeptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String param) {
        super("Registro não encontrado com o parâmetro: " + param);
    }

}
