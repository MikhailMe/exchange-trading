package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Client;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.TRANSFER)
public final class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "money")
    private long money;

    @Column(name = "date")
    private Instant date;

    @JoinColumn(name = "admin_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin approver;

    public Transfer(@NotNull final Client client,
                    final long money,
                    @NotNull final Instant date,
                    @NotNull final Admin approver) {
        this.client = client;
        this.money = money;
        this.date = date;
        this.approver = approver;
    }

}
