package com.bot.demo.vo;

import com.bot.demo.annotation.PatchIgnore;
import com.bot.demo.util.DateTimeDeserializer;
import com.bot.demo.util.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Pattern;


@Document("accountbooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    @Field(name="_id")
    @PatchIgnore
    @JsonIgnore
    private ObjectId id;
    @JsonIgnore
    private ObjectId user;
    private Integer amount;
    private Boolean isFixed;
    private String category;
    private String content;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime date;
    private Integer accountId;
    @Pattern(regexp = "^\\d+[dwmy]$")
    private String fixedDuration;
}
