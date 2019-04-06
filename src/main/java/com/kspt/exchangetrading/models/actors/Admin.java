package com.kspt.exchangetrading.models.actors;

import com.kspt.exchangetrading.models.util.enums.PersonType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;

import static com.kspt.exchangetrading.configuration.Constants.ADMIN;

@Data
@Entity
@Table(name = ADMIN)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {

    public Admin(@NotNull final String name,
                 @NotNull final String surname) {
        super(name, surname, PersonType.ADMIN);
    }
}
