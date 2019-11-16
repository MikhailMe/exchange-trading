package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.ADMIN_REQUEST)
public class AdminRequest extends Request {

    @Column(name = "brokerId")
    protected Long to;

    @Column(name = "adminId")
    protected Long from;

    @Column(name = "data")
    private String data;

}
