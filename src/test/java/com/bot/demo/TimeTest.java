package com.bot.demo;

import com.bot.demo.service.StudyService;
import com.bot.demo.util.TimeUtils;
import com.bot.demo.vo.Study;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class TimeTest {
    @Autowired StudyService studyService;
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
    public void timeDiffer() {
        String startTimeStr = "2022-04-02";
        DateTime startTime = DateTime.parse(startTimeStr);
        int result = TimeUtils.timeDifferMinute(startTime, startTime.plusHours(59));
        int result2 = TimeUtils.timeDifferMinute(startTime, startTime.plusHours(59).plusMillis(1000*60*5));
        System.out.println(result);
        System.out.println(result2);


        Study study = new Study();
        study.setStartDate(DateTime.parse("2022-05-05T01:12:05"));
        study.setEndDate(DateTime.parse("2022-05-06T08:12:05"));
        System.out.println(studyService.studyTimeMap(study));


        study.setStartDate(DateTime.parse("2022-05-05T01:12:05"));
        study.setEndDate(DateTime.parse("2022-05-05T08:12:05"));
        System.out.println(studyService.studyTimeMap(study));
    }
}
