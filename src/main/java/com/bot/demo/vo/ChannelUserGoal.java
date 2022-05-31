package com.bot.demo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("channelusergoals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelUserGoal {
    @Id
    @Field(name="_id")
    private ObjectId id;
    private Channel channel;
    private User user;
    private int goalTime;
    private String content;
}
