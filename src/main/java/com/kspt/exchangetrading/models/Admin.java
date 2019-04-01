package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import javax.persistence.Table;

@Data
//@Table(name = "admins")
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {

    public Admin() {
        this.personType = PersonType.ADMIN;
    }
}
