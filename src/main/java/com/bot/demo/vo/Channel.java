package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("channels")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Id
    private String _id;
    private String channelId;
    private List<User> userList;
    private String name;
}
