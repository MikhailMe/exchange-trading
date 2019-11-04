package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.models.actors.Person;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Request {

    private Person to;

    private Person from;

    private Instant date;

    Request(@NotNull final Person from,
            @NotNull final Person to,
            @NotNull final Instant date) {
        this.from = from;
        this.to = to;
        this.date = date;
    }
}
