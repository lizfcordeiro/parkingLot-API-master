package com.tomorrow.ParkingLotSystem.handler;

import com.tomorrow.ParkingLotSystem.domain.error.ResourceNotFoundError;
import com.tomorrow.ParkingLotSystem.domain.error.ValidationError;
import com.tomorrow.ParkingLotSystem.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        ResourceNotFoundError error = new ResourceNotFoundError(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                new Date().getTime(),
                exception.getClass().getName()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String, String> fields = new HashMap<>();
        for (ObjectError error: exception.getBindingResult().getAllErrors()) {
            String name = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            fields.put(name, message);
        }

        ValidationError error = new ValidationError(
                "Invalid Fields",
                HttpStatus.BAD_REQUEST.value(),
                "Field Validation Error" ,
                new Date().getTime(),
                exception.getClass().getName(),
                fields
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
