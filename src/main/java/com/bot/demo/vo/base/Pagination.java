package com.bot.demo.vo.base;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pagination {
    private int page;
    private int pagePerCnt;
    private int total;
}
