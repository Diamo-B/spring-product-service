package com.example.productservice.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionsHandler {

    @ExceptionHandler({NoProductsException.class})
    public ResponseEntity<Object> handleNoProductsFoundException(NoProductsException Ex){
        ProductExceptionResponse res =  new ProductExceptionResponse(Ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidProductDataException.class})
    public ResponseEntity<Object> handleInvalidProductsDataException(InvalidProductDataException Ex){
        ProductExceptionResponse res =  new ProductExceptionResponse(Ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
