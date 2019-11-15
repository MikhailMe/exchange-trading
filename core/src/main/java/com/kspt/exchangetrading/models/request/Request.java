package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class Request extends AbstractEntity {

    protected Instant date;

    Request(@NotNull final Instant date) {
        this.date = date;
    }
}
