package com.spaghettininjas.yaposs.standarts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeStandard {
    private DateTimeStandard(){}
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String getCurrentFormattedDateTime() {
        return LocalDateTime.now().format(formatter);
    }
    public static LocalDateTime getDateTimeFromString(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
