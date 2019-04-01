package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import javax.persistence.Table;

@Data
//@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    public Client() {
        this.personType = PersonType.CLIENT;
    }

}
