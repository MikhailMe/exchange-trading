package com.kspt.exchangetrading.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.kspt.exchangetrading.models.util.enums.RequestType;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"requestType", "hibernateLazyInitializer", "handler"})
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    protected String description;

    @Column(name = "date")
    protected Instant date;

    @Transient
    @JsonIgnore
    protected RequestType requestType;

    public Request(@NotNull final String description,
                   @NotNull final Instant date,
                   @NotNull final RequestType type) {
        this.description = description;
        this.date = date;
        this.requestType = type;
    }
}
