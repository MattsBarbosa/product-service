package com.ms.product.controllers.exceptions;

import lombok.Getter;

@Getter
public class ErrorHandle {
    private String field;
    private String message;

    public ErrorHandle(String field, String defaultMessage) {
        this.field = field;
        this.message = defaultMessage;
    }
}
