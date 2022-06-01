package com.bot.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("studies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    @Id
    @Field(name="_id")
    @JsonIgnore
    private ObjectId id;
    private String title;
    private List<Comment> commentList;
    private DateTime startDate;
    private DateTime endDate;
    @JsonIgnore
    private ObjectId owner;

    @Data
    class Comment{
        @Id
        @Field(name="_id")
        @JsonIgnore
        private ObjectId id;
        private String title;
        private String comment;
        private DateTime date;
        private Boolean isSecret;
    }
}
