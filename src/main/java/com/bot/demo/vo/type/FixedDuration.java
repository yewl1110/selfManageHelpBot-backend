package com.bot.demo.vo.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum FixedDuration {
    DAY("1d"), WEEK("1w"), MONTH("1m"), YEAR("1y");
    private final String value;
    FixedDuration(String value) {
        this.value = value;
    }
    static final FixedDuration DEFAULT = MONTH;

    public static boolean contains(String value) {
        return Arrays.stream(values()).map(FixedDuration::getValue).anyMatch(e->e.equals(value));
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static String fromJson(@JsonProperty("fixedDuration") String code) {

        if(contains(code)) {
            return code;
        } else {
            return DEFAULT.getValue();
        }
    }
}
