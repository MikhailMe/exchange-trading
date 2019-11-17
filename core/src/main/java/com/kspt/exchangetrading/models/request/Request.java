package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Request extends AbstractEntity {

    @Column(name = "date")
    protected Instant date;

    @Column(name = "requestType")
    protected String requestType;

}

