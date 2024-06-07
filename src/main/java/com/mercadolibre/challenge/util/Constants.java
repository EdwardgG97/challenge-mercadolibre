package com.mercadolibre.challenge.util;

import lombok.Getter;

@Getter
public enum Constants {

    ACCESS_KEY("access_key"),
    LANGUAGE("language"),
    ES("es"),
    IP_NOT_FOUND("IP not found"),
    INVALID_ACCESS_KEY("Invalid access key"),
    INVALID_IP_FORMAT("Invalid IP address format"),
    UNKNOWN_ERROR("Unexpected error"),
    COUNTRY_NOT_FOUND("No se encontraron registros en la base de datos");

    private final String msg;

    Constants(String msg) {
        this.msg = msg;
    }

}
