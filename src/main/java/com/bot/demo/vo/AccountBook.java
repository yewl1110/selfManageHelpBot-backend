package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;


@Document("accountbooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    @Field("_id")
    private String id;
    private String userId;
    private Integer amount;
    private Boolean isFixed;
    private String category;
    private String content;
    private LocalDate date;
    private Integer accountId;

    public void set_id(String id) {
        this.id = id;
    }
    public String get_id() {
        return id;
    }
}
