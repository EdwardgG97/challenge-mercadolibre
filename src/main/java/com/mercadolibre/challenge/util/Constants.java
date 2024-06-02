package com.mercadolibre.challenge.util;

import lombok.Getter;

@Getter
public enum Constants {

    ACCESS_KEY("access_key"),
    LANGUAGE("language"),
    ES("es"),
    INVALID_IP_ADDRESS("Invalid ip address"),
    INVALID_ACCESS_KEY("Invalid access key"),
    INVALID_FIELDS("Invalid fields"),
    UNKNOWN_ERROR_CODE("Unknown error code");

    private final String msg;

    Constants(String msg) {
        this.msg = msg;
    }

}
