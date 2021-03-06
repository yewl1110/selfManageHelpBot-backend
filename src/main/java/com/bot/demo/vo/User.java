package com.bot.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class User {
    @Id
    @Field(name="_id")
    private ObjectId id;
    private String userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;
    private String nickname;
    private List<ObjectId> channelList;
    private List<ObjectId> studyList;
    private List<ObjectId> todoList;
    private String accessKey;
    private String discordId;
}
