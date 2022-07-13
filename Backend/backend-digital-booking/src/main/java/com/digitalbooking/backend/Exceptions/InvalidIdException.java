package com.digitalbooking.backend.Exceptions;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException() {
        super("el id no puede ser null o 0, ni contener valores negativo");
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
