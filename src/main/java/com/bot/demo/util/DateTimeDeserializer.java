package com.bot.demo.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {
    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        try {
            JsonToken currentToken = p.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                String dateTimeAsString = p.getText().trim();
                return DateTime.parse(dateTimeAsString);
            }
        } catch (Exception e) {

        }
        return null;
    }
}
