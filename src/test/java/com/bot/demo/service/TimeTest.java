package com.bot.demo.service;

import com.bot.demo.util.TimeUtils;
import com.bot.demo.vo.Study;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TimeTest {
    @Autowired StudyService studyService;
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
        ReflectionTestUtils.invokeMethod(studyService, "studyTimeMap", study);

        study.setStartDate(DateTime.parse("2022-05-05T01:12:05"));
        study.setEndDate(DateTime.parse("2022-05-05T08:12:05"));
        ReflectionTestUtils.invokeMethod(studyService, "studyTimeMap", study);
    }

}
