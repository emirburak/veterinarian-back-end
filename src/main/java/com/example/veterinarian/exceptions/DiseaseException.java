package com.example.veterinarian.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class DiseaseException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public DiseaseException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public DiseaseException(String s) {
        this.errorMessage=s;
    }
}
