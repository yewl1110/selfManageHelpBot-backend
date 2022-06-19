package com.bot.demo.controller.vo;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserAccount {
    @NotEmpty
    @Pattern(regexp="^[A-z0-9]{6,12}$")
    private String userId;
    @NotEmpty
    @Pattern(regexp = "^[A-z0-9!@^()&*\\-_=+]{6,30}$")
    private String passwd;
    private String encryptedPasswd;
    @NotEmpty
    @Pattern(regexp = "^[A-z0-9가-힣]{2,10}$")
    private String nickname;
    @Nullable
    @Pattern(regexp = "^[0-9]{18}$")
    private String discordId;

    public String getEncryptedPasswd() {
        return new BCryptPasswordEncoder().encode(passwd);
    }
}
