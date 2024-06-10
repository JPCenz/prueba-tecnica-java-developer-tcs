package com.jpcenz.prueba.interfaces.rest.exception;

public class CustomErrorException extends Exception{
    public CustomErrorException(String message) {
        super(message);
    }
}
