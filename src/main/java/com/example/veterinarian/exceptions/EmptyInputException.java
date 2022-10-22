package com.example.veterinarian.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class EmptyInputException extends  RuntimeException{
    private String errorCode;
    private String errorMessage;

}
