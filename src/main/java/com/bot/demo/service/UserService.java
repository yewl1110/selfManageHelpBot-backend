package com.bot.demo.service;

import com.bot.demo.controller.vo.SimpleMessageDTO;
import com.bot.demo.controller.vo.UserAccount;
import com.bot.demo.respository.UsersRepo;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    final private UsersRepo userRepository;

    public User getUser(User _user) {
        User loginUser = null;
        try {
            User user = userRepository.findByUserId(_user.getUserId());
            if(BCrypt.checkpw(_user.getPasswd(), user.getPasswd())) {
                loginUser = new User();
                loginUser.setUserId(user.getUserId());
                loginUser.setNickname(user.getNickname());
                loginUser.setDiscordId(user.getDiscordId());
            }
        } catch (Exception e) {

        }
        return loginUser;
    }
    
    public SimpleMessageDTO signup(UserAccount userAccount) {
        SimpleMessageDTO  result = SimpleMessageDTO.builder().code(0).msg("회원가입 실패").build();
        boolean validation = true;
        User newMember = null;

        // 값 중복체크 등
        if(validation) {
            validation = !isExistUserId(userAccount.getUserId());
        }
        if(validation) {
            // 다른것들
        }

        if(validation) {
            User user = User.builder()
                    .userId(userAccount.getUserId())
                    .nickname(userAccount.getNickname())
                    .passwd(userAccount.getEncryptedPasswd())
                    .discordId(userAccount.getDiscordId())
                    .channelList(new ArrayList<>())
                    .studyList(new ArrayList<>())
                    .todoList(new ArrayList<>())
                    .build();
            newMember = userRepository.insert(user);

            if(!ObjectUtils.isEmpty(newMember)) {
                result = SimpleMessageDTO.builder().code(1).msg("회원가입 성공").build();
            }
        }

        return result;
    }

    public SimpleMessageDTO checkUserId(String userId) {
        SimpleMessageDTO result;
        if(isExistUserId(userId)) {
            result = SimpleMessageDTO.builder().msg("이미 사용중인 ID").code(0).build();
        } else {
            result = SimpleMessageDTO.builder().code(1).build();
        }
        return result;
    }

    private boolean isExistUserId(String userId) {
        boolean result = true;
        try {
            User user = userRepository.findByUserId(userId);
            if(user == null) {
                result = false;
            }
        } catch (Exception e) {

        }
        return result;
    }
}
