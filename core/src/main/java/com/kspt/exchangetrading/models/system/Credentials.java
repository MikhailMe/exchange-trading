package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = Constants.CREDENTIALS)
public final class Credentials extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public Credentials(@NotNull final String login,
                       @NotNull final String password) {
        this.login = login;
        this.password = password;
    }

}
