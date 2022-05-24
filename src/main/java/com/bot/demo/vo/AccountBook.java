package com.bot.demo.vo;

import com.bot.demo.annotation.PatchIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.thymeleaf.util.StringUtils;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Document("accountbooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    @Id
    @PatchIgnore
    private String _id;
    private String userId;
    private Integer amount;
    private Boolean isFixed;
    private String category;
    private String content;
    private LocalDate date;
    private Integer accountId;
//    private FixedDuration fixedDuration = FixedDuration.MONTH;
    @Pattern(regexp = "^\\d+[dwmy]$")
    private String fixedDuration = "1m";

    public String getFixedDuration() {
        if(StringUtils.isEmpty(fixedDuration)) return "1m";
        return fixedDuration;
    }
}
