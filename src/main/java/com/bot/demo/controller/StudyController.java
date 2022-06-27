package com.bot.demo.controller;

import com.bot.demo.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {
    final private StudyService studyService;

    @PostMapping("time")
    Map<String, Object> studyTimeByDate(@RequestBody Map<String, Object> param) {
        return studyService.getStudyTimeByDate(param);
    }

    @PostMapping("weeks/{week}")
    ResponseEntity weeksData(@PathVariable("week") String _week, String userId) {
        ResponseEntity result;
        try {
            Integer week = Integer.valueOf(_week);
            result = ResponseEntity.ok(studyService.getWeeksTimeData(userId, week));
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return result;
    }
}
