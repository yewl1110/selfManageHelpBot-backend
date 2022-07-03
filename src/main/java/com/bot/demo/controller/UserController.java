package com.bot.demo.controller;

import com.bot.demo.controller.vo.SimpleMessageDTO;
import com.bot.demo.controller.vo.UserAccount;
import com.bot.demo.service.UserService;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        ResponseEntity<User> result;
        User loginUser = userService.getUser(user);
        if(ObjectUtils.isEmpty(loginUser)) {
            result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else {
            result = ResponseEntity.ok(loginUser);
        }
        return result;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody UserAccount user) {
//        return ResponseEntity.ok(SimpleMessageDTO.builder().code(1).msg("회원가입 성공").build());
        return ResponseEntity.ok(userService.signup(user));
    }

    @GetMapping("/checkUserId")
    public ResponseEntity checkUserId(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(userService.checkUserId(userId));
    }
}
