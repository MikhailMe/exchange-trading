package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = Constants.ADMIN_REQUEST)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminRequest extends Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data")
    private String data;

    public AdminRequest(@NotNull final Person from,
                        @NotNull final Person to,
                        @NotNull final Instant date) {
        super(from, to, date);
    }
}
