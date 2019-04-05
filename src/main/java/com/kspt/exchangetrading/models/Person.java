package com.kspt.exchangetrading.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"personType", "hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "name", nullable = false)
    protected String name;
    @Column(name = "surname", nullable = false)
    protected String surname;
    @Transient
    @JsonIgnore
    protected PersonType personType;
}
