package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("counters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counter {
    @Id
    private String _id;
    private String name;
    private int seq_value;
}
