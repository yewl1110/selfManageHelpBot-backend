package com.bot.demo.util;

import java.util.*;

public class DataUtils {
    public static LinkedHashMap<String, Object> sortMapByKey(Map map) {
        List<Map.Entry<String, Object>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
