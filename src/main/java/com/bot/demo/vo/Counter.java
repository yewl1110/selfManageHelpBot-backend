package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("counters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counter {
    @Id
    @Field(name="_id")
    private ObjectId id;
    private String name;
    private int seq_value;
}
