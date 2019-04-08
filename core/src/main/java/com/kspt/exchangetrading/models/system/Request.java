package com.kspt.exchangetrading.models.system;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

import static com.kspt.exchangetrading.configuration.Constants.REQUEST;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = REQUEST)
public final class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    protected Instant date;

    @Column(name = "status")
    private boolean status;

    @Column(name = "request_type")
    protected String requestType;

    public Request(@NotNull final Instant date,
                   @NotNull final String type) {
        this.date = date;
        this.requestType = type;
    }
}
