package com.bot.demo.vo;

import com.bot.demo.annotation.PatchIgnore;
import com.bot.demo.vo.type.FixedDuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


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
    private FixedDuration fixedDuration = FixedDuration.MONTH;
}
