package com.desafioudstecnologia.exeptions;

import java.util.List;

public class DuplicateRecordException extends RuntimeException {

    public DuplicateRecordException(String field) {
        super("O campo " + field + " já existe no banco de dados.");
    }

}
