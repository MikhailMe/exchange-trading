package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

// TODO: what about unite models ?
// TODO: made single request model

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Request.CLIENT_REQUEST)
public final class ClientRequest extends Request {

    @Column(name = "adminId")
    private Long adminId;

    @Column(name = "brokerId")
    private Long brokerId;

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "fromType")
    private String fromType;

    @Column(name = "toType")
    private String toType;

    @Column(name = "status")
    private String status;

    public ClientRequest(@NotNull final Long brokerId,
                         @NotNull final Long clientId,
                         @NotNull final String fromType,
                         @NotNull final String toType,
                         @NotNull final Long quantity,
                         @NotNull final String requestType) {
        super(requestType);
        this.brokerId = brokerId;
        this.clientId = clientId;
        this.fromType = fromType;
        this.toType = toType;
        this.quantity = quantity;
        this.status = Constants.ClientRequestStatus.PROCESSING;
    }

}
