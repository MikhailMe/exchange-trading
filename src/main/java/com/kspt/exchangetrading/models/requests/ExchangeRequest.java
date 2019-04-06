package com.kspt.exchangetrading.models.requests;


import com.kspt.exchangetrading.models.util.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.kspt.exchangetrading.configuration.Constants.REQUEST;

@Data
@Entity
@Table(name = REQUEST)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExchangeRequest extends Request {

    public ExchangeRequest(RequestType type) {
        this.requestType = type;
    }

}
