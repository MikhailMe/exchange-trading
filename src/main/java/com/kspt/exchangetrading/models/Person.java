package com.kspt.exchangetrading.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;*/

@Data
//@Entity
//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    //@Column(name = "name", nullable = false)
    protected String name;
    //@Column(name = "surname", nullable = false)
    protected String surname;
    //@Column(name = "personType", nullable = false)
    protected PersonType personType;

}
