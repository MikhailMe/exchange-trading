package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.models.actors.Person;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Request extends AbstractEntity {

    protected Person to;

    protected Person from;

    @DateTimeFormat
    protected Instant date;

    Request(@NotNull final Person from,
            @NotNull final Person to,
            @NotNull final Instant date) {
        this.from = from;
        this.to = to;
        this.date = date;
    }
}
