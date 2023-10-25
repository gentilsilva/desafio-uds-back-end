package com.desafioudstecnologia.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {

    public static LocalDate searchFormat(String date) {
        DateTimeFormatter baseDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate baseDate = LocalDate.parse(date, baseDateFormat);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String newDate = baseDate.format(formatter);

        return LocalDate.parse(newDate, formatter);
    }

    public static LocalDate registerFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

}
