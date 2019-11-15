package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.ADMIN_REQUEST)
public class AdminRequest extends Request {

    @Column(name = "data")
    private String data;

    public AdminRequest(@NotNull final Person from,
                        @NotNull final Person to,
                        @NotNull final Instant date) {
        super(from, to, date);
    }
}
