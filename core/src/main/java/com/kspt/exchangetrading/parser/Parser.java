package com.kspt.exchangetrading.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kspt.exchangetrading.models.system.Passport;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;

@Primary
public class Parser {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper().findAndRegisterModules();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(REQUIRE_SETTERS_FOR_GETTERS, false);
        mapper.configure(AUTO_DETECT_GETTERS, true);
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
