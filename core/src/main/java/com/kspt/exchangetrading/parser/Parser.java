package com.kspt.exchangetrading.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.kspt.exchangetrading.models.system.Credentials;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class Parser {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static Credentials parseCredentials(@NotNull final Object object) {
        Credentials credentials = null;
        try {
            String json = mapper.writeValueAsString(object);
            credentials = mapper.readValue(json, new TypeReference<Credentials>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

}
