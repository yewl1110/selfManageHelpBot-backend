package com.bot.demo.vo;


import com.bot.demo.vo.base.Pagination;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("todos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todos {
    @Id
    private String _id;
    private String content;
    private Boolean isCompleted;
    private String owner;
    private Integer proceed;
    private LocalDateTime date;
    private Integer todoId;
}
