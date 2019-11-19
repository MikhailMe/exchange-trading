package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.system.Asset;
import com.kspt.exchangetrading.models.system.Stock;
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
@Table(name = Constants.Request.ADMIN_REQUEST)
public class AdminRequest extends Request {

    @Column(name = "clientId")
    private Long to;

    @Column(name = "adminId")
    private Long from;

    @Column(name = "asset")
    private Asset asset;

    @Column(name = "stock")
    private Stock stock;

    @Column(name = "approver")
    private Long approverId;

    public AdminRequest(@NotNull final String requestType) {
        super(requestType);
    }
}
