package com.dfsp.dashboard.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static java.sql.Date toSqlDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date parsedDate = format.parse(date);

        return new java.sql.Date(parsedDate.getTime());

    }
}
