package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Request.CLIENT_REQUEST)
public class ClientRequest extends Request {

    @Column(name = "brokerId")
    protected Long to;

    @Column(name = "clientId")
    protected Long from;

    @Column(name = "amount")
    protected Long amount;

    // ruble, dollar, euro or mishcoin, realtyincome, cloudflare
    @Column(name = "assetType")
    protected String assetType;

    public ClientRequest(@NotNull final Long to,
                         @NotNull final Long from,
                         @NotNull final Long amount,
                         @NotNull final String assetType,
                         @NotNull final String requestType) {
        super(requestType);
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.assetType = assetType;
    }

}
