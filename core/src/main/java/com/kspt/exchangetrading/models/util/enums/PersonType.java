package com.kspt.exchangetrading.models.util.enums;

import lombok.Getter;
import lombok.Setter;

public enum PersonType {

    ADMIN(0),
    BROKER(1),
    CLIENT(2),
    VIP_ADMIN(3),
    VIP_BROKER(4);

    @Getter
    @Setter
    private int code;

    PersonType(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (code) {
            case 0:
                return ADMIN.toString();
            case 1:
                return BROKER.toString();
            case 2:
                return CLIENT.toString();
            case 3:
                return VIP_ADMIN.toString();
            case 4:
                return VIP_BROKER.toString();
            default:
                return "invalid person type";
        }
    }
}
