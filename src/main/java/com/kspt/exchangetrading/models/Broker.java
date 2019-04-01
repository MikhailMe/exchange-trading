package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import javax.persistence.Table;

@Data
//@Table(name = "brokers")
@EqualsAndHashCode(callSuper = true)
public class Broker extends Person {

    public Broker() {
        this.personType = PersonType.BROKER;
    }

}
