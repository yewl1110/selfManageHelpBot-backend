package com.bot.demo.service;

import com.bot.demo.respository.StudiesRepo;
import com.bot.demo.respository.UsersRepo;
import com.bot.demo.util.DataUtils;
import com.bot.demo.util.TimeUtils;
import com.bot.demo.util.type.Days;
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
            DateTime endDate = startDate.plusDays(count - 1);

            list = studyRepository.findAllByOwnerAndEndDateAfterAndStartDateBefore(user.getId(), startDate, endDate);

            List<DateTime> dateList = TimeUtils.dateTimes(startDate, endDate);
            Map<String, Integer> dateSet = studyListToStudyTimeMap(dateList, list);

            result.put("data", dateSet);
        } catch (Exception e) {

        }

        return result;
    }

    /**
     * ..n주까지 (4주아니면8주)
     * 4주
     * 3주
     * 2주
     * 1주                    -2일 -1일 오늘
     *       목  금   토   일   월   화   수
     *                                (오늘요일)
     * */
    public Map<String, Object> getWeeksTimeData(String userId, int week) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = usersRepository.findByUserId(userId);
            DateTime startDate = DateTime.now().plusDays(-1);
            DateTime endDate = startDate.plusDays(week * 7 * -1);
            List<Object> weekList = new ArrayList<>();
            List<String> days = new ArrayList<>();

            for(int i = 0; i < week; i++) {
                List<DateTime> subList = new ArrayList<>();
                subList.addAll(TimeUtils.dateBeforeTimes(startDate.plusDays(-7*i), 7));
                List<Study> dataList = studyRepository.findAllByOwnerAndEndDateAfterAndStartDateBefore(user.getId(), subList.get(0), subList.get(subList.size()-1));
                Map<String, Integer> studyTimeMap = studyListToStudyTimeMap(subList, dataList);
                weekList.add(DataUtils.sortMapByKey(studyTimeMap));
            }
            Collections.reverse(weekList);

            for(DateTime dateTime : TimeUtils.dateBeforeTimes(startDate.plusDays(-7), 7)) {
                int dayValue = dateTime.getDayOfWeek();
                days.add(Days.getDaysByValue(dayValue).KOR);
            }

            result.put("data", weekList);
            result.put("dayLabel", days);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Map<String, Integer> studyListToStudyTimeMap(List<DateTime> dayList, List<Study> dataList) {
        Map<String, Integer> dateSet = new HashMap<>();
        dayList.forEach(date -> {
            dateSet.put(TimeUtils.getDateStr(date), 0);
        });

        List<Map<String, Integer>> flatData = dataList.stream().map(this::studyTimeMap).collect(Collectors.toList());
        for(String date : dateSet.keySet()) {
            Optional<Integer> minute = flatData.stream().filter(e-> e.containsKey(date)).map(e->e.get(date)).reduce(Integer::sum);
            dateSet.put(date, minute.orElse(0));
        }

        return dateSet;
    }

    private Map<String, Integer> studyTimeMap(Study study) {
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
