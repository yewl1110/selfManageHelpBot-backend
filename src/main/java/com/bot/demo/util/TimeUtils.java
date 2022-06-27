package com.bot.demo.util;

import org.joda.time.DateTime;

import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeUtils {
    public static List<DateTime> dateTimes(DateTime startDate,@Positive int count) {
        List<DateTime> result = new ArrayList<>();

        String startDateStr = startDate.toString().split("T")[0];
        DateTime startIdxDate = DateTime.parse(startDateStr);

        for(int i = 0; i < count; i++) {
            result.add(startIdxDate.plusDays(i));
        }

        return result;
    }

    public static List<DateTime> dateBeforeTimes(DateTime startDate,@Positive int count) {
        List<DateTime> result = new ArrayList<>();

        String startDateStr = startDate.toString().split("T")[0];
        DateTime startIdxDate = DateTime.parse(startDateStr);

        for(int i = 0; i < count; i++) {
            result.add(startIdxDate.plusDays(i*-1));
        }
        Collections.reverse(result);
        return result;
    }

    public static List<DateTime> dateTimes(DateTime _startDate, DateTime _endDate) {
        List<DateTime> result = new ArrayList<>();

        String startDateStr = _startDate.toString().split("T")[0];
        String endDateStr = _endDate.toString().split("T")[0];

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        int betweenDays = (int)ChronoUnit.DAYS.between(startDate, endDate);

        DateTime startIdxDate = DateTime.parse(startDateStr);

        for(int i = 0; i <= betweenDays; i++) {
            result.add(startIdxDate.plusDays(i));
        }

        return result;
    }

    public static int timeDifferMinute(DateTime _startTime, DateTime _endTime) {
        LocalDateTime startTime = LocalDateTime.parse(_startTime.toString().split("\\.")[0]);
        LocalDateTime endTime = LocalDateTime.parse(_endTime.toString().split("\\.")[0]);
        int differ = (int)ChronoUnit.MINUTES.between(startTime, endTime);
        return differ;
    }

    public static String getDateStr(DateTime dateTime) {
        return dateTime.toString().split("T")[0];
    }
}
