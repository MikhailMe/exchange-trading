package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.CREDENTIALS)
public final class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
