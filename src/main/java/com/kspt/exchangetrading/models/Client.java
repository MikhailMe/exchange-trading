package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "client")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    public Client() {
        this.personType = PersonType.CLIENT;
    }

}
