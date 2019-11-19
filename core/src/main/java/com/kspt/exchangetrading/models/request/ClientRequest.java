package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.system.Asset;
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

    @Column(name = "asset")
    private Asset asset;

    @Column(name = "status")
    private String status;

    public ClientRequest(@NotNull final Long to,
                         @NotNull final Long from,
                         @NotNull final Asset asset,
                         @NotNull final String requestType) {
        super(requestType);
        this.to = to;
        this.from = from;
        this.asset = asset;
        this.status = "processing";
    }

}
