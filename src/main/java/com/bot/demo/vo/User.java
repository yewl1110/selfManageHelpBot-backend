package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Field(name="_id")
    private ObjectId id;
    private String userId;
    private String nickname;
    private List<ObjectId> channelList;
    private List<ObjectId> studyList;
    private List<ObjectId> todoList;
    private String accessKey;
}
