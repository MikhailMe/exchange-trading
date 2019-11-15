package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Broker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.ADMIN_REQUEST)
public class AdminRequest extends Request {

    @JoinColumn(name = "broker_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Broker to;

    @JoinColumn(name = "admin_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Admin from;

    @Column(name = "data")
    private String data;

    public AdminRequest(@NotNull final Instant date) {
        super(date);
    }
}
