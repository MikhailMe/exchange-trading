package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
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
@Table(name = Constants.BROKER_REQUEST)
public class BrokerRequest extends Request {

    @Column(name = "adminId")
    protected Admin to;

    @Column(name = "brokerId")
    protected Long from;

    @Column(name = "money")
    private long money;

}
