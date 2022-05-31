package com.bot.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            serializers.defaultSerializeValue(value.toString(), gen);
        } catch (Exception e) {
            serializers.defaultSerializeValue("", gen);
        }
    }
}
