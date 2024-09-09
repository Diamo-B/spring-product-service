package com.example.productservice.exceptions;

public class NoProductsException extends RuntimeException {
    public NoProductsException(String message) {
        super(message);
    }
}
