package com.rtcab.cfd.web.screens;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

@Component
public class DateConversion {


    public LocalDate startOfWeek(LocalDate referenceDate, Locale locale) {
        final DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        return referenceDate.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
    public Date toDate(LocalDate localDate) {
        return toDate(localDate.atTime(9,0));
    }
    public LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
