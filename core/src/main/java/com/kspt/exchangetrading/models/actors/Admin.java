package com.kspt.exchangetrading.models.actors;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.Actor.ADMIN)
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Broker> brokers;

    public Admin(@NotNull final String name,
                 @NotNull final String surname,
                 @NotNull final String personType) {
        this.name = name;
        this.surname = surname;
        this.personType = personType;
    }
}

