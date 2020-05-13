package com.aushev.plantstore.exception;

public class ManufacturerAlreadyExistException extends RuntimeException{
    public ManufacturerAlreadyExistException(String message) {
        super(message);
    }
}
