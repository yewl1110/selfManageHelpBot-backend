package com.bot.demo.vo;


import com.bot.demo.vo.base.Pagination;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("todos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @Field(name="_id")
    private ObjectId id;
    private String content;
    private Boolean isCompleted;
    private String owner;
    private Integer proceed;
    private LocalDateTime date;
    private Integer todoId;
}
