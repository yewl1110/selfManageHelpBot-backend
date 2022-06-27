package com.bot.demo.util.type;

public enum Days {
    MON(1,"월", "MON"),
    TUE(2,"화", "TUE"),
    WED(3,"수", "WED"),
    THU(4,"목", "THU"),
    FRI(5,"금", "FRI"),
    SAT(6,"토", "SAT"),
    SUN(7,"일", "SUN");

    Days(int value, String kor, String en) {
        this.VALUE = value;
        this.KOR = kor;
        this.EN = en;
    }
    public final int VALUE;
    public final String KOR;
    public final String EN;

    public static Days getDaysByValue(int value) {
        Days result = null;
        for(Days days : Days.values()) {
            if(days.VALUE == value) {
                result = days;
                break;
            }
        }
        return result;
    }
}
