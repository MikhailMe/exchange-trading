package com.kspt.exchangetrading.models.actors;

import com.kspt.exchangetrading.models.util.enums.PersonType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;

import static com.kspt.exchangetrading.configuration.Constants.CLIENT;

@Data
@Entity
@Table(name = CLIENT)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    public Client(@NotNull final String name,
                  @NotNull final String surname) {
        super(name, surname, PersonType.CLIENT);
    }

}
