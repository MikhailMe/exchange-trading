package com.kspt.exchangetrading.models.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.models.util.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class BrokerageAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", nullable = false)
    private long number;

    @Column(name = "money")
    private long money;

    @Transient
    @JsonIgnore
    private Currency currency;

    @Column(name = "creation_time")
    private Instant creationTime;

}
