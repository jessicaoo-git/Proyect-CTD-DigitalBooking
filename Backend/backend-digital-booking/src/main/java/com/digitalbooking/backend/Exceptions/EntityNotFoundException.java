package com.digitalbooking.backend.Exceptions;


public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
