package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.BANK)
public final class BankRecord extends AbstractEntity {

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "type")
    private String type;

    public BankRecord(@NotNull final String type,
                      @NotNull final Double quantity) {
        this.type = type;
        this.quantity = quantity;
    }

}
