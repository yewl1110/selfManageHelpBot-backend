package com.bot.demo.lib;

import com.bot.demo.util.type.Days;
import com.bot.demo.util.TimeUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeTest {
    @Test
    public void strToTime() {
        String dateStr = "2022-05-05";
        int count = 5;
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        System.out.println(localDate.plusDays(count));

        String startTimeStr = "2022-05-05T01:12:05";
        String endTimeStr = "2022-05-06T01:12:05";
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ISO_DATE_TIME);
        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration.getSeconds());

        System.out.println("1d".matches("^\\d+[dwmy]$"));
    }

    @Test
    public void localDateToLocalDateTime() {

        String dateStr = "2022-05-05";
        int count = 5;
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println(zonedDateTime);
    }

    @Test
    public void timeSearchTest() {
        String startDateStr = "2019-02-05";
        String endDateStr = "2022-04-02";

        List<DateTime> list = TimeUtils.dateTimes(DateTime.parse(startDateStr), DateTime.parse(endDateStr));
        System.out.println(list);

        String startDateStr2 = "2022-04-02";
        String endDateStr2 = "2022-04-02";

        List<DateTime> list2 = TimeUtils.dateTimes(DateTime.parse(startDateStr2), DateTime.parse(endDateStr2));
        System.out.println(list2);
    }

    @Test
    void daysOfWeek() {
        int week = 3;
        DateTime now = DateTime.now();
        System.out.println(now.plusDays(-1).dayOfWeek().get()); // 1~7 월~일
        List<Object> list = new ArrayList<>();
        List<String> days = new ArrayList<>();
        for(int i = 0; i < week; i++) {
            List<DateTime> subList = new ArrayList<>();
            subList.addAll(TimeUtils.dateBeforeTimes(now.plusDays(-7*i), 7));
            list.add(subList);
        }
        for(DateTime dateTime : (List<DateTime>)list.get(0)) {
            int dayValue = dateTime.getDayOfWeek();
            days.add(Days.getDaysByValue(dayValue).KOR);
        }
        Collections.reverse(list);
        System.out.println(list);
        System.out.println(days);
    }
}
