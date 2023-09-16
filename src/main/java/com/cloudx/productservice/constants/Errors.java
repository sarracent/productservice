package com.cloudx.productservice.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {
    ERROR_BUSINESS_VALIDATION_NULL_OR_EMPTY("100001", "Error el campo %s es nulo o vacio"),
    ERROR_DATABASE_PRODUCT_NOT_FOUND("200101", "Error no se encontraron registros en Product con el valor %s"),
    ERROR_PRODUCT_NOT_VALUE("200102", "Error no se encotró producto con el valor %s"),

    ERROR_GENERAL("900000", "Error General -> [%s] %s");

    private final String code;
    private final String message;

}