package com.bot.demo.controller.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SimpleMessageDTO {
    String msg;
    int code;  // 1: 성공 0: 실패

    public static SimpleMessageDTO getDefaultFailed() {
        return SimpleMessageDTO.builder().msg("실패했습니다.").code(0).build();
    }

    public static SimpleMessageDTO getDefaultSuccess() {
        return SimpleMessageDTO.builder().msg("성공했습니다.").code(1).build();
    }
}
