package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.ASSET)
public final class Asset extends AbstractEntity {

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "type")
    private String type;

    @Column(name = "quantity")
    private Double quantity;

    public Asset(@NotNull final Long clientId,
                 @NotNull final String type) {
        this.clientId = clientId;
        this.type = type;
        this.quantity = 0d;
    }
}
