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

    protected Instant date;

}

