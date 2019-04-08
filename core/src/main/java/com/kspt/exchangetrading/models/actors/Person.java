package com.kspt.exchangetrading.models.actors;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "surname", nullable = false)
    protected String surname;

    @Column(name = "person_type")
    protected String personType;

    public Person(@NotNull final String name,
                  @NotNull final String surname,
                  @NotNull final String type) {
        this.name = name;
        this.surname = surname;
        this.personType = type;
    }
}
