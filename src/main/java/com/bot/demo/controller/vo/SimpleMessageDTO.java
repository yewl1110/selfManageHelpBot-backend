package com.bot.demo.controller.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SimpleMessageDTO {
    String msg;
    int code;  // 1: 성공 0: 실패
}
