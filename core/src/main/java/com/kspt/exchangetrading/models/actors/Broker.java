package com.kspt.exchangetrading.models.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.system.Agreement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.Actor.BROKER)
@EqualsAndHashCode(callSuper = true)
public final class Broker extends Person {

    @Column(name = "adminId")
    private Long adminId;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Agreement> agreements;

    public Broker(@NotNull final String name,
                  @NotNull final String surname,
                  @NotNull final String personType) {
        this.name = name;
        this.surname = surname;
        this.personType = personType;
    }
}
