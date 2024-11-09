package com.scaler.ECOMUserService.exception;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(){}

    public UserNotFoundException(String message){
        super(message);
    }
}
