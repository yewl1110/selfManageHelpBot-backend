package com.bot.demo.service;

import com.bot.demo.respository.StudiesRepo;
import com.bot.demo.vo.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyService {
    final StudiesRepo studyRepository;

    public Map<String, Object> getStudyByDate(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String dateStr = String.valueOf(param.get("startDate"));
        int count = Integer.parseInt(String.valueOf(param.get("count")));
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        Random random = new Random();

        for(int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.plusDays(i));
            map.put("time", random.nextInt() % 20 + 20);
            map.put("test", random.nextInt() % 20 + 20);
            list.add(map);
        }

        double average = list.stream()
                .map(e -> (HashMap)e)
                .mapToDouble(e->Double.parseDouble(String.valueOf(e.get("time"))))
                .average().getAsDouble();

        result.put("list", list);
        result.put("average", String.format("%.1f", average));
        return result;
    }


    public Map<String, Object> getStudyByDate2(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String dateStr = String.valueOf(param.get("startDate"));
        int count = Integer.parseInt(String.valueOf(param.get("count")));
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        Random random = new Random();

        for(int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.plusDays(i));
            map.put("time", random.nextInt() % 20 + 20);
            map.put("test", random.nextInt() % 20 + 20);
            list.add(map);
        }

        double average = list.stream()
                .map(e -> (HashMap)e)
                .mapToDouble(e->Double.parseDouble(String.valueOf(e.get("time"))))
                .average().getAsDouble();

        result.put("list", list);
        result.put("average", String.format("%.1f", average));
        return result;
    }
}
