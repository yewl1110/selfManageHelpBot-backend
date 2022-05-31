package com.bot.demo;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JodaTimeTest {
    @Test
    public void timezone() {
        String timeStr = "2022-05-05";
        DateTime dateTime = DateTime.parse(timeStr);
        System.out.println(dateTime); // 2022-05-05T00:00:00.000+09:00
    }
}
