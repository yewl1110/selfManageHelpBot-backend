package com.bot.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("studies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    @Id
    private String _id;
    private String title;
    //{ title: ‘제목’, content: ‘내용’ , date: Date.now, isSecret: false}
//    private List<Object> commentList;
    private String commentList;
    private String startDate;
    private String endDate;
    private Object owner;
}
