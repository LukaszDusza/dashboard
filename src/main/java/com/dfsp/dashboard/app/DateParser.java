package com.dfsp.dashboard.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static java.sql.Date toSqlDate(String date) {
        java.sql.Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = format.parse(date);
            result = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
