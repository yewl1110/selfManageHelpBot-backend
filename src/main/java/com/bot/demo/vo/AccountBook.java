package com.bot.demo.vo;

import com.bot.demo.annotation.PatchIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.thymeleaf.util.StringUtils;

import javax.validation.constraints.Pattern;


@Document("accountbooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    @Field(name="_id")
    @PatchIgnore
    private ObjectId id;
    private ObjectId user;
    private Integer amount;
    private Boolean isFixed;
    private String category;
    private String content;
    private DateTime date;
    private Integer accountId;
    @Pattern(regexp = "^\\d+[dwmy]$")
    private String fixedDuration = "1m";

    public String getFixedDuration() {
        if(StringUtils.isEmpty(fixedDuration)) return "1m";
        return fixedDuration;
    }
}
