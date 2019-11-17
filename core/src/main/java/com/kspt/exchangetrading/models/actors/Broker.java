package com.kspt.exchangetrading.models.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.request.BrokerRequest;
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
@Table(name = Constants.BROKER)
@EqualsAndHashCode(callSuper = true)
public class Broker extends Person {

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Agreement> agreements;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<BrokerRequest> requests;

    public Broker(@NotNull final String name,
                  @NotNull final String surname,
                  @NotNull final String personType) {
        this.name = name;
        this.surname = surname;
        this.personType = personType;
    }
}
