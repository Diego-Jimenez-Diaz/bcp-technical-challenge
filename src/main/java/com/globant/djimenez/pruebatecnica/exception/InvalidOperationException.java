package com.globant.djimenez.pruebatecnica.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(){

    }

    public InvalidOperationException(String message){
        super(message);
    }
}
