package com.bot.demo.service;

import com.bot.demo.respository.UsersRepo;
import com.bot.demo.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    final private UsersRepo userRepository;

    public Map<String, Object> getUser(User _user) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userRepository.findByUserId(_user.getUserId());
            if(BCrypt.checkpw(_user.getPasswd(), user.getPasswd())) {
                result.put("nickname", user.getNickname());
                result.put("discordId", user.getDiscordId());
            }
        } catch (Exception e) {

        }
        return result;
    }
}
