package com.example.veterinarian.advice;


import com.example.veterinarian.exceptions.DiseaseException;
import com.example.veterinarian.exceptions.EmptyInputException;
import com.example.veterinarian.exceptions.FalseSecureCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.CredentialNotFoundException;
import java.util.NoSuchElementException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
        return new ResponseEntity<>("Input field is empty, please fill.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException noSuchElementException){
        return new ResponseEntity<>("There is not the value you look for in database.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException entityNotFoundException){
        return new ResponseEntity<>("There is not such an entity you look for in the database", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CredentialNotFoundException.class)
    public ResponseEntity<String> handleCredentialException(CredentialNotFoundException credentialNotFoundException){
        return new ResponseEntity<>("The given password is wrong for the user", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleCredentialException(AccessDeniedException unauthorizedException){
        return new ResponseEntity<>("You have no authorization to make this operation", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DiseaseException.class)
    public ResponseEntity<String> handleCredentialException(DiseaseException unauthorizedException){
        return new ResponseEntity<>("There is not such a disease defined. Please let us know by mail.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FalseSecureCodeException.class)
    public ResponseEntity<String> handleCredentialException(FalseSecureCodeException unauthorizedException){
        return new ResponseEntity<>("You did not provide the right secure code.", HttpStatus.EXPECTATION_FAILED);
    }
}
