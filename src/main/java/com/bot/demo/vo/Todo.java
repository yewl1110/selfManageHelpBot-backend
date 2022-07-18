package com.bot.demo.vo;


import com.bot.demo.vo.base.Pagination;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Document("todos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @Field(name="_id")
    @JsonIgnore
    private ObjectId id;
    @Size(min = 5, max=100)
    @NotBlank
    private String content;
    private Boolean isCompleted;
    @JsonIgnore
    private ObjectId owner;
    private Integer proceed;
    private LocalDateTime insDate;
    private LocalDateTime updDate;
    private Integer todoId;
}
