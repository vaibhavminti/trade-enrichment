package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements Enricher {
    private static Logger LOGGER = LoggerFactory.getLogger(DateValidator.class);
    @Override
    public Trade enrich(Trade inputTrade) {
        String date = inputTrade.getDate();
        date = date.replaceAll("\\s", "");
        if (date != null) {
            try {
                LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
                return inputTrade;

            } catch (DateTimeParseException e) {
                //e.printStackTrace();
                LOGGER.error("Date " + date + " not in yyyyMMdd format");
            }
        }
        return null;
    }
}
