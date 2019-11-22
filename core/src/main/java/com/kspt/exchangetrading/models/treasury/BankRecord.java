package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.ASSET)
public final class BankRecord extends AbstractEntity {

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "type")
    private String type;

}
