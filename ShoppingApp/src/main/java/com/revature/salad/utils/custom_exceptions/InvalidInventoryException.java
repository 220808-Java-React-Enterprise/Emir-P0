package com.revature.salad.utils.custom_exceptions;

public class InvalidInventoryException extends RuntimeException {

    public InvalidInventoryException(String message){
        super(message);
    }
}
