package com.kspt.exchangetrading.models;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Request.CLIENT_REQUEST)
public final class ClientRequest extends AbstractEntity {

    @Column(name = "date")
    protected Instant date;

    @Column(name = "adminId")
    private Long adminId;

    @Column(name = "brokerId")
    private Long brokerId;

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "fromType")
    private String fromType;

    @Column(name = "toType")
    private String toType;

    @Column(name = "status")
    private String status;

    @Column(name = "requestType")
    protected String requestType;

    public ClientRequest(@NotNull final Long brokerId,
                         @NotNull final Long clientId,
                         @NotNull final String fromType,
                         @NotNull final String toType,
                         @NotNull final Double quantity,
                         @NotNull final String requestType) {
        this.brokerId = brokerId;
        this.clientId = clientId;
        this.fromType = fromType;
        this.toType = toType;
        this.quantity = quantity;
        this.date = Instant.now();
        this.requestType = requestType;
        this.status = Constants.ClientRequestStatus.PROCESSING;
    }

}
