package com.kspt.exchangetrading.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kspt.exchangetrading.models.system.Passport;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Parser {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    public static Passport parsePassport(@NotNull final Object object) {
        try {
            String json = mapper.writeValueAsString(object);
            return mapper.readValue(json, new TypeReference<Passport>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
