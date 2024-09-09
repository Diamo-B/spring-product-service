package com.example.productservice.exceptions;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ProductExceptionResponse {
    private final String message;
    private final ZonedDateTime timestamp;

    public ProductExceptionResponse(String message) {
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }
}
