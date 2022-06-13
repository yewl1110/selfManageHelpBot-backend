package com.bot.demo.controller;

import com.bot.demo.service.StudyService;
import lombok.RequiredArgsConstructor;
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
}
