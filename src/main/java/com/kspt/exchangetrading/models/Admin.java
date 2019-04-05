package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin")
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {

    public Admin() {
        this.personType = PersonType.ADMIN;
    }
}
