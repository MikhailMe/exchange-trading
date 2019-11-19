package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
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
@Table(name = Constants.System.ASSET)
@EqualsAndHashCode(callSuper = true)
public final class Asset extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "quantity")
    private Long quantity;

    public Asset(@NotNull final String type,
                 @NotNull final Long quantity) {
        this.type = type;
        this.quantity = quantity;
    }

}
