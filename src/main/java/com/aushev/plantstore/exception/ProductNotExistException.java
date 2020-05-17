package com.aushev.plantstore.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String message) {
        super(message);
    }
}
