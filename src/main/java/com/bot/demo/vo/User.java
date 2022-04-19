package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String _id;
    private String userId;
    private String nickname;
    private List<Channel> channelList;
    private List<Study> studyList;
    private List<Todo> todoList;
    private String accessKey;
}
