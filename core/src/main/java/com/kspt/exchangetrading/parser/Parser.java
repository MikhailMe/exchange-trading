package com.kspt.exchangetrading.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Passport;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;


public class Parser {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static Passport parsePassport(@NotNull final Object object) {
        Passport passport = null;
        try {
            String json = mapper.writeValueAsString(object);
            passport = mapper.readValue(json, new TypeReference<Passport>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passport;
    }

    public static Client parseClient(@NotNull final Object object) {
        Client client = null;
        try {
            String json = mapper.writeValueAsString(object);
            Map<String, String> clientMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
            client = new Client(clientMap.get("name"), clientMap.get("surname"), clientMap.get("personType"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

}
