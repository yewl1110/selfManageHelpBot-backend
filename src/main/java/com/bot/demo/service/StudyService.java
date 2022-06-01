package com.bot.demo.service;

import com.bot.demo.respository.StudiesRepo;
import com.bot.demo.respository.UsersRepo;
import com.bot.demo.util.TimeUtils;
import com.bot.demo.vo.Study;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {
    final StudiesRepo studyRepository;
    final UsersRepo usersRepository;

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


    public Map<String, Object> getStudyTimeByDate(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Study> list = new ArrayList<>();
        try {
            String userId = String.valueOf(param.get("userId"));
            String dateStr = String.valueOf(param.get("startDate"));
            int count = Integer.parseInt(String.valueOf(param.get("count")));

            User user = usersRepository.findByUserId(userId);
            DateTime startDate = DateTime.parse(dateStr);
            DateTime endDate = startDate.plusDays(count);

            list = studyRepository.findAllByOwnerAndEndDateAfterAndStartDateBefore(user.getId(), startDate, endDate);


            Map<String, Integer> dateSet = new HashMap<>();
            List<DateTime> dateList = TimeUtils.dateTimes(startDate, endDate);
            dateList.forEach(date -> {
                dateSet.put(TimeUtils.getDateStr(date), 0);
            });

            List<Map<String, Integer>> flatData = list.stream().map(this::studyTimeMap).collect(Collectors.toList());
            for(String date : dateSet.keySet()) {
                Optional<Integer> minute = flatData.stream().filter(e-> e.containsKey(date)).map(e->e.get(date)).reduce(Integer::sum);
                dateSet.put(date, minute.orElse(0));
            }
            result.put("data", dateSet);
        } catch (Exception e) {

        }

        return result;
    }

    public Map<String, Integer> studyTimeMap(Study study) {
        Map<String, Integer> result = new HashMap<>();

        List<DateTime> list = TimeUtils.dateTimes(study.getStartDate(), study.getEndDate());
        if(list.size() <= 1) {
            result.put(
                    TimeUtils.getDateStr(study.getStartDate()),
                    TimeUtils.timeDifferMinute(study.getStartDate(), study.getEndDate())
            );
        } else {
            list.remove(0);
            list.add(0, study.getStartDate());
            list.add(study.getEndDate());

            for(int i = 1; i < list.size(); i++) {
                result.put(
                        TimeUtils.getDateStr(list.get(i-1)),
                        TimeUtils.timeDifferMinute(list.get(i-1), list.get(i))
                );
            }
        }

        return result;
    }
}
