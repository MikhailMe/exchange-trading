package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.kspt.exchangetrading.models.util.enums.PersonType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"personType", "hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "surname", nullable = false)
    protected String surname;

    @Transient
    @JsonIgnore
    protected PersonType personType;

    public Person(@NotNull final String name,
                  @NotNull final String surname,
                  @NotNull final PersonType type) {
        this.name = name;
        this.surname = surname;
        this.personType = type;
    }
}
