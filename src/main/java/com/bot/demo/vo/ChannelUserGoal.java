package com.bot.demo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("channelusergoals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelUserGoal {
    @Id
    private String _id;
    private Channel channel;
    private User user;
    private int goalTime;
    private String content;
}
