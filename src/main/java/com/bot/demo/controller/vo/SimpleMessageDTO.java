package com.bot.demo.controller.vo;

import lombok.Builder;

@Builder
public class SimpleMessageDTO {
    String msg;
    int code;  // 1: 성공 0: 실패
}
