package com.bot.demo.controller;

import com.bot.demo.service.UserService;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public Map<String, Object> login(@RequestBody User user) {
        return userService.getUser(user);
    }
}
