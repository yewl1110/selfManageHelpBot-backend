package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("accountbooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    private String _id;
    private User user;
    private int amount;
    private boolean isFixed;
    private String category;
    private String content;
    private String date;
    private int accountId;
}
