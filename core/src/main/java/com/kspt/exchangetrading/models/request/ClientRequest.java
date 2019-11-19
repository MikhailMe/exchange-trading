package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Request.CLIENT_REQUEST)
public class ClientRequest extends Request {

    @Column(name = "adminId")
    private Long adminId;

    @Column(name = "brokerId")
    private Long to;

    @Column(name = "clientId")
    private Long from;

    @Column(name = "amount")
    private Long amount;

    // processing, approved, declined
    @Column(name = "status")
    private String status;

    // ruble, dollar, euro or mishcoin, realtyincome, cloudflare
    @Column(name = "assetType")
    private String assetType;

    public ClientRequest(@NotNull final Long to,
                         @NotNull final Long from,
                         @NotNull final Long amount,
                         @NotNull final String assetType,
                         @NotNull final String requestType) {
        super(requestType);
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.status = "processing";
        this.assetType = assetType;
    }

}
