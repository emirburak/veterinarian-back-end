package com.example.veterinarian.exceptions;

public class FalseSecureCodeException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public FalseSecureCodeException(String message) {
        this.errorMessage=message;
    }
}
