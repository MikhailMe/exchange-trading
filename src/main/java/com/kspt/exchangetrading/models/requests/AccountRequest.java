package com.kspt.exchangetrading.models.requests;

import com.kspt.exchangetrading.models.util.enums.Currency;
import com.kspt.exchangetrading.models.util.Passport;
import com.kspt.exchangetrading.models.util.enums.RequestType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;

import java.time.Instant;

import static com.kspt.exchangetrading.configuration.Constants.REQUEST;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = REQUEST)
@EqualsAndHashCode(callSuper = true)
public final class AccountRequest extends Request {

    private Passport passport;

    private Currency currency;

    public AccountRequest(@NotNull final String description,
                          @NotNull final Instant date,
                          @NotNull final RequestType type,
                          @NotNull final Passport passport,
                          @NotNull final Currency currency) {
        super(description, date, type);
        this.passport = passport;
        this.currency = currency;
    }

    public AccountRequest(@NotNull final RequestType type) {
        super();
        this.requestType = type;
    }

}
