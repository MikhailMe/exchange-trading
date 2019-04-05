package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "broker")
@EqualsAndHashCode(callSuper = true)
public class Broker extends Person {

    public Broker() {
        this.personType = PersonType.BROKER;
    }

}
