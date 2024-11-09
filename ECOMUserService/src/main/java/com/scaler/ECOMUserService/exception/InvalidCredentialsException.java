package com.scaler.ECOMUserService.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){}

    public InvalidCredentialsException(String message){
        super(message);
    }
}
